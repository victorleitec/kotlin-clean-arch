package com.example.demo.domain.usecases

import com.example.demo.domain.datasources.TodoDataSource
import com.example.demo.domain.models.TodoModel
import com.example.demo.domain.repositories.TodoRepository
import java.util.UUID

class TodoUseCases(private val dataSource: TodoDataSource) : TodoRepository {

    override fun getAll(): Result<List<TodoModel>> {
        return Result.success(dataSource.getAll())
    }

    override fun getById(id: UUID): Result<TodoModel> {
        return when (val todo = dataSource.getById(id)) {
            null -> Result.failure(Exception("Todo not found by ID"))
            else -> Result.success(todo)
        }
    }

    override fun create(todo: TodoModel): Result<TodoModel> {
        val errors = validateTodo(todo)
        return when {
            errors.isNotEmpty() -> Result.failure(Exception(errors.joinToString(separator = "; ")))
            else -> Result.success(dataSource.create(todo))
        }
    }

    override fun update(id: UUID, updatedTodo: TodoModel): Result<TodoModel> {
        return when (dataSource.getById(id)) {
            null -> Result.failure(Exception("Todo not found by ID"))
            else -> Result.success(dataSource.update(id, updatedTodo)!!)
        }
    }

    override fun delete(id: UUID): Result<Boolean> {
        return when (dataSource.getById(id)) {
            null -> Result.failure(Exception("Todo not found by ID"))
            else -> Result.success(dataSource.delete(id))
        }
    }

    private fun validateTodo(todo: TodoModel): List<String> {
        val errors = mutableListOf<String>()

        if (todo.title.isBlank()) {
            errors.add("Todo title cannot be empty")
        }

        if (todo.description.isBlank()) {
            errors.add("Todo description cannot be empty")
        }

        return errors
    }
}