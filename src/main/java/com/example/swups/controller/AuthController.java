package com.example.swups.controller;

import com.example.swups.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }
}
