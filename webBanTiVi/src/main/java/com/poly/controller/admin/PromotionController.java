package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class PromotionController {
    @GetMapping("/manager/promotion")
    public String promotionPage(HttpSession session){
        session.setAttribute("view","/admin/page/promotion.html");
        return "admin/layout.html";
    }
}
