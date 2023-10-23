package com.poly.controller.user1;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController1 {

    @GetMapping("/")
    public String loadHome(HttpSession session){
        session.setAttribute("pageView","/user1/page/home/home.html");
        return "/user1/index";
    }
    @GetMapping("/tivi")
    public String loadProduct(HttpSession session){
        session.setAttribute("pageView","/user1/page/product/tivi.html");
        return "/user1/index";
    }
    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session){
        session.setAttribute("pageView","/user1/page/product/accessory.html");
        return "/user1/index";
    }
    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session){
        session.setAttribute("pageView","/user1/page/invoice/search_invoice.html");
        return "/user1/index";
    }
}
