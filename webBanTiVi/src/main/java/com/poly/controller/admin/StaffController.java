package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class StaffController {
    @GetMapping("/manager/staff")
    public String pageStaff(HttpSession session){
        session.setAttribute("view","/admin/page/staff.html");
        return "/admin/layout.html";
    }
}
