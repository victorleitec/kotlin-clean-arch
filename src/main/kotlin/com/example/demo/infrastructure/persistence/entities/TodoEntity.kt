package com.example.demo.infrastructure.persistence.entities

import com.example.demo.domain.models.TodoModel
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "tb_todos")
class TodoEntity(
        @Id
        @Column(name = "id", nullable = false)
        val id: UUID,

        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "description", nullable = false)
        var description: String,

        @Column(name = "done", nullable = false)
        var done: Boolean = false
) {
    constructor() : this(UUID.randomUUID(), "", "", false)

    fun toModel(): TodoModel {
        return TodoModel(id = this.id, title = this.title, description = this.description, done = this.done)
    }
    
    companion object {
        fun fromModel(model: TodoModel): TodoEntity {
            val id = model.id ?: UUID.randomUUID()
            return TodoEntity(id, model.title, model.description, model.done)
        }
    }
}