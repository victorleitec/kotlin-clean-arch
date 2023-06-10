package com.example.demo.domain.datasources

import com.example.demo.domain.models.TodoModel
import java.util.*

interface TodoDataSource {
    fun getAll(): List<TodoModel>
    fun getById(id: UUID): TodoModel?
    fun create(todo: TodoModel): TodoModel
    fun update(id: UUID, todo: TodoModel): TodoModel?
    fun delete(id: UUID): Boolean
}