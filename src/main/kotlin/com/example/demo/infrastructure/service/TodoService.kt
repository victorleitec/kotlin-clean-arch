package com.example.demo.infrastructure.service

import com.example.demo.domain.datasources.TodoDataSource
import com.example.demo.domain.models.TodoModel
import com.example.demo.infrastructure.persistence.entities.TodoEntity
import com.example.demo.infrastructure.persistence.repositories.TodoJpaRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoService(private val jpaRepository: TodoJpaRepository) : TodoDataSource {

    override fun getAll(): List<TodoModel> {
        return jpaRepository.findAll().map { it.toModel() }
    }

    override fun getById(id: UUID): TodoModel? {
        return jpaRepository.findById(id).map { it.toModel() }.orElse(null)
    }

    override fun create(todo: TodoModel): TodoModel {
        val entity = TodoEntity.fromModel(todo)
        return jpaRepository.save(entity).toModel()
    }

    override fun update(id: UUID, todo: TodoModel): TodoModel? {
        return jpaRepository.findById(id).map { existing ->
            val updatedEntity = existing.apply {
                title = todo.title
                description = todo.description
                done = todo.done
            }
            jpaRepository.save(updatedEntity).toModel()
        }.orElse(null)
    }

    override fun delete(id: UUID): Boolean {
        return jpaRepository.existsById(id).also { exists ->
            if (exists) jpaRepository.deleteById(id)
        }
    }
}