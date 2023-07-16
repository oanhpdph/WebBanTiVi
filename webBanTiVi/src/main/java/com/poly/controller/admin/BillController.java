package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BillController {
     @GetMapping("/manager/bill")
    public String billPage(HttpSession session){
        session.setAttribute("view","/admin/page/bill.html");
        return "/admin/layout.html";
    }
}
