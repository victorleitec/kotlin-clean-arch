package com.example.demo.infrastructure.service

import com.example.demo.domain.models.TodoModel
import com.example.demo.infrastructure.persistence.entities.TodoEntity
import com.example.demo.infrastructure.persistence.repositories.TodoJpaRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoServiceImpl(private val jpaRepository: TodoJpaRepository) : TodoService {

    override fun findAll(): List<TodoModel> {
        return jpaRepository.findAll().map { it.toModel() }
    }

    override fun findById(id: UUID): TodoModel? {
        return jpaRepository.findById(id).map { it.toModel() }.orElse(null)
    }

    override fun create(todo: TodoModel): TodoModel {
        val todoEntity = TodoEntity.fromModel(todo)
        return jpaRepository.save(todoEntity).toModel()
    }

    override fun update(id: UUID, todo: TodoModel): TodoModel {
        val todoEntity = TodoEntity.fromModel(todo.copy(id = id))
        return jpaRepository.save(todoEntity).toModel()
    }

    override fun delete(id: UUID): Boolean {
        return jpaRepository.findById(id).map {
            jpaRepository.deleteById(it.id)
            true
        }.orElse(false)
    }
}