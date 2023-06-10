package com.example.demo.infrastructure.persistence.repositories

import com.example.demo.infrastructure.persistence.entities.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TodoJpaRepository : JpaRepository<TodoEntity, UUID>