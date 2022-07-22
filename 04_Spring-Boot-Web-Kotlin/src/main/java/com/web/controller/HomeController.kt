package com.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

// Hajin Kim's idea
@Controller
class HomeController {
    @GetMapping("")
    fun home(): String {
        return "home"
    }
}
