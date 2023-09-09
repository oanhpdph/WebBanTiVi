package com.poly.controller.login;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {

        return "login/page/login";
    }

    @GetMapping("/register")
    public String register() {
        return "login/page/register";
    }

    @GetMapping("/forgot-password")
    public String forgot() {
        return "login/page/forgot-password";
    }



}
