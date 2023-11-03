package com.poly.controller.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController1 {

    @GetMapping("/")
    public String loadHome(HttpSession session){
        session.setAttribute("pageView","/user/page/home/home.html");
        return "/user/index";
    }
    @GetMapping("/tivi")
    public String loadProduct(HttpSession session){
        session.setAttribute("pageView","/user/page/product/tivi.html");
        return "/user/index";
    }
    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session){
        session.setAttribute("pageView","/user/page/product/accessory.html");
        return "/user/index";
    }
    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session){
        session.setAttribute("pageView","/user/page/invoice/search_invoice.html");
        return "/user/index";
    }
    @GetMapping("/profile")
    public String profile(HttpSession session){
        session.setAttribute("pageView","/user/page/profile/profile.html");
        return "/user/index";
    }
}
