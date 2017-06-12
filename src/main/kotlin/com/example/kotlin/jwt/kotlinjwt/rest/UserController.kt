package com.example.kotlin.jwt.kotlinjwt.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Nemanja on 6/12/17.
 */
@RestController
class UserController {

    @GetMapping("/users")
    fun getUsers(): String {
        return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
                "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
    }
}