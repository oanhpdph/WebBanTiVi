package com.poly.controller.user;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OrderController {


    @GetMapping("/layout_order/completed")
    public String completed(HttpSession session){
        session.setAttribute("pageViewOrder", "/user/page/_order/_order_complete.html");
        return "user/layout";
    }
    @GetMapping("/layout_order/processing")
    public String processing(HttpSession session){
        session.setAttribute("pageViewOrder", "/user/page/_order/_order_processing.html");
        return "user/layout";
    }
    @GetMapping("/layout_order/detail")
    public String detail(HttpSession session){
        session.setAttribute("pageViewOrder", "/user/page/_order/_order_detail.html");
        return "user/layout";
    }


}

