package com.example.kotlin.jwt.kotlinjwt

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KotlinjwtApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotlinjwtApplication::class.java, *args)
}
