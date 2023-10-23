package com.poly.controller.newUser;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class NewUserController {
    @GetMapping("")
    public String index(HttpSession session){
        session.setAttribute("pageView","/user1/page/home/home.html");
        return "/user1/index";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session){
        session.setAttribute("pageView","/user1/page/profile/profile.html");
        return "/user1/index";
    }
}
