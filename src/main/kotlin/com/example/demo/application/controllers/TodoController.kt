package com.example.demo.application.controllers

import com.example.demo.domain.models.TodoModel
import com.example.demo.domain.usecases.TodoUseCases
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/todos")
class TodoController(private val useCases: TodoUseCases) {

    @GetMapping
    fun getAll(): ResponseEntity<List<TodoModel>> {
        val result = useCases.getAll()
        return result.map { ResponseEntity.ok(it) }
                .getOrElse { ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyList()) }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<TodoModel> {
        val uuid: UUID = try {
            UUID.fromString(id)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }

        val result = useCases.getById(uuid)
        return result.map { ResponseEntity.ok(it) }
                .getOrElse { ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) }
    }

    @PostMapping
    fun create(@RequestBody todo: TodoModel): ResponseEntity<TodoModel> {
        val result = useCases.create(todo)
        return result.map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
                .getOrElse { ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null) }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody updatedTodo: TodoModel): ResponseEntity<TodoModel> {
        val result = useCases.update(id, updatedTodo)
        return result.map { ResponseEntity.ok(it) }
                .getOrElse { ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Void> {
        val result = useCases.delete(id)
        return result.map { ResponseEntity.noContent().build<Void>() }
                .getOrElse { ResponseEntity.notFound().build() }
    }
}