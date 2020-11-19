package com.example.demo.backend

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun root(): String = "redirect:/index"

    @GetMapping("/index")
    fun index(): String = "index"
}