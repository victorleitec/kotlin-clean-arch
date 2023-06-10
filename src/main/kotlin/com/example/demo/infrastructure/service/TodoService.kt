package com.example.demo.infrastructure.service

import com.example.demo.domain.models.TodoModel
import java.util.UUID

interface TodoService {
    fun findAll(): List<TodoModel>
    fun findById(id: UUID): TodoModel?
    fun create(todo: TodoModel): TodoModel
    fun update(id: UUID, todo: TodoModel): TodoModel
    fun delete(id: UUID): Boolean
}