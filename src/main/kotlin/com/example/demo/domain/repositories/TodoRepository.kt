package com.example.demo.domain.repositories

import com.example.demo.domain.models.TodoModel
import java.util.UUID


interface TodoRepository {
    fun getAll(): Result<List<TodoModel>>
    fun getById(id: UUID): Result<TodoModel>
    fun create(todo: TodoModel): Result<TodoModel>
    fun update(id: UUID, updatedTodo: TodoModel): Result<TodoModel>
    fun delete(id: UUID): Result<Boolean>
}   