package com.example.demo.domain.models

import java.util.UUID

data class TodoModel(
        val id: UUID? = null,
        val title: String,
        val description: String,
        val done: Boolean = false,
)