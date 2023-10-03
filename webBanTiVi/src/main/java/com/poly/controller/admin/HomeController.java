package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
    @GetMapping("")
    public String loadHome(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active","/dashboard");
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active","/dashboard");
        return "admin/layout";
    }

    @GetMapping("/order")
    public String loadBill(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/order/order.html");
        session.setAttribute("active","/order");
        return "admin/layout";
    }

    @GetMapping("/direct_sales")
    public String loadDirectSales(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/direct_sales/directSales.html");
        return "admin/page/direct_sales/directSales";
    }

    @GetMapping("/customer/list")
    public String loadCustomer(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/customer/customer.html");
        session.setAttribute("active","/customer/list");
        return "admin/layout";
    }
    @GetMapping("/customer/rank")
    public String loadRank(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/customer/rank_customer.html");
        session.setAttribute("active","/customer/rank");
        return "admin/layout";
    }
    @GetMapping("/staff")
    public String loadStaff(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/staff.html");
        session.setAttribute("active","/staff");
        return "admin/layout";
    }

    @GetMapping("/position")
    public String loadPosition(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/position.html");
        session.setAttribute("active","/position");

        return "admin/layout";
    }

    @GetMapping("/product")
    public String loadProduct(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/product/product.html");
        session.setAttribute("active","/product");
        return "admin/layout";
    }

    @GetMapping("/statistic")
    public String loadStatistic(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active","/statistic");
        return "admin/layout";
    }
    @GetMapping("/blog")
    public String loadBlog(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/blog/blog.html");
        session.setAttribute("active","/blog");
        return "admin/layout";
    }

}
