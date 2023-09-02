package com.poly.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public String loadHome(HttpSession session){
        session.setAttribute("pageView","/admin/page/dashboard.html");
        return "admin/layout";
    }



    @GetMapping("/admin/dashboard")
    public String loadDashboard(HttpSession session){
    session.setAttribute("pageView","/admin/page/dashboard.html");
        return "admin/layout";
    }
    @GetMapping("/admin/bill")
    public String loadBill(HttpSession session){
        session.setAttribute("pageView","/admin/page/bill.html");
        return "admin/layout";
    }
    @GetMapping("/admin/direct_sales")
    public String loadDirectSales(HttpSession session){
        session.setAttribute("pageView","/admin/page/directSales.html");
        return "admin/layout";
    }
    @GetMapping("/admin/customer")
    public String loadCustomer(HttpSession session){
        session.setAttribute("pageView","/admin/page/customer.html");
        return "admin/layout";
    }
    @GetMapping("/admin/staff")
    public String loadStaff(HttpSession session){
        session.setAttribute("pageView","/admin/page/staff.html");
        return "admin/layout";
    }

    @GetMapping("/Login")
    public String loadLogin(HttpSession session){
        session.setAttribute("pageView","/admin/page/dashboard.html");
        return "admin/layout";
    }
}
