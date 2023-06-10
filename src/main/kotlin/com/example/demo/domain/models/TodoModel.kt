package com.example.demo.domain.models

import java.util.UUID

data class TodoModel(
        val id: UUID = UUID.randomUUID(),
        val title: String,
        val description: String,
        val done: Boolean = false,
)