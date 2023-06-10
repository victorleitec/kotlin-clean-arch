package com.example.demo.infrastructure.persistence.repositories

import com.example.demo.infrastructure.persistence.entities.TodoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TodoJpaRepository : JpaRepository<TodoEntity, UUID>