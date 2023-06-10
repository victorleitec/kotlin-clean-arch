package com.example.demo.infrastructure.persistence.entities

import com.example.demo.domain.models.TodoModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "todos")
class TodoEntity(
        @Id
        @Column(name = "id", nullable = false)
        val id: UUID,

        @Column(name = "title", nullable = false)
        val title: String,

        @Column(name = "description", nullable = false)
        val description: String,

        @Column(name = "done", nullable = false)
        val done: Boolean = false
) {
    constructor() : this(UUID.randomUUID(), "", "", false)

    fun toModel(): TodoModel {
        return TodoModel(id = this.id, title = this.title, description = this.description, done = this.done)
    }
    
    companion object {
        fun fromModel(model: TodoModel): TodoEntity {
            return TodoEntity(model.id, model.title, model.description, model.done)
        }
    }
}