package com.example.demo.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessagesController {

    @GetMapping("/messages")
    fun messages(): Array<String> = arrayOf("Message 1", "Message 2", "Message 3")
}