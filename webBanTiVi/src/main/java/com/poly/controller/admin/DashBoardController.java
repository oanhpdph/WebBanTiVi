package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashBoardController {
    @GetMapping("/dashboard")
    public String homePage(HttpSession session) {
        session.setAttribute("view", "/admin/index.html");

        return "/admin/layout.html";
    }
}
