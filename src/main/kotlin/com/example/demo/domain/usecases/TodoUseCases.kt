package com.example.demo.domain.usecases

import com.example.demo.domain.models.TodoModel
import com.example.demo.domain.repositories.TodoRepository
import java.util.UUID

class TodoUseCases(private val repository: TodoRepository) {

    fun getAll(): Result<List<TodoModel>> {
       return repository.getAll()
    }

    fun getById(id: UUID): Result<TodoModel> {
        return validateId(id)
    }

    fun create(todo: TodoModel): Result<TodoModel> {
        val errors = validateTodo(todo)
        
        return errors?.let {
            Result.failure(Exception(errors.joinToString(separator = "; ")))
        } ?: repository.create(todo)
    }

    fun update(id: UUID, updatedTodo: TodoModel): Result<TodoModel> {
        val validation = validateId(id)
        if (validation.isFailure) {
            return validation
        }

        return repository.update(id, updatedTodo)
    }

    fun delete(id: UUID): Result<Boolean> {
        val validation = validateId(id)
        if (validation.isFailure) {
            return validation.map { false }
        }

        return repository.delete(id)
    }

    private fun validateId(id: UUID): Result<TodoModel> {
        return repository.getById(id).takeIf { it.isSuccess } ?: Result.failure(Exception("Todo not found by ID"))
    }
    
    private fun validateTodo(todo: TodoModel): List<String>? {
        val errors = mutableListOf<String>()

        if (todo.title.isBlank()) {
            errors.add("Todo title cannot be empty")
        }

        if (todo.description.isBlank()) {
            errors.add("Todo description cannot be empty")
        }

        return errors.takeIf { it.isNotEmpty() }
    }
}