package com.example.demo.application.config

import com.example.demo.domain.usecases.TodoUseCases
import com.example.demo.infrastructure.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Autowired
    private lateinit var todoService: TodoService

    @Bean
    fun todoUseCases(): TodoUseCases {
        return TodoUseCases(todoService)
    }
}