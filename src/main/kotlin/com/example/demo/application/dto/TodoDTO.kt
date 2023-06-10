package com.example.demo.application.dto

import com.example.demo.domain.models.TodoModel
import java.util.UUID

data class TodoDTO(
    val title: String,
    val description: String,
    val done: Boolean
)

// Converter TodoModel em TodoDTO
fun TodoModel.toDTO(): TodoDTO {
    return TodoDTO(
        title = this.title,
        description = this.description,
        done = this.done
    )
}

// Converter TodoDTO em TodoModel
fun TodoDTO.toModel(id: UUID): TodoModel {
    return TodoModel(
        id = id,
        title = this.title,
        description = this.description,
        done = this.done
    )
}