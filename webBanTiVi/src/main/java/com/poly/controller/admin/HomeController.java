package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public String loadHome(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        return "admin/layout";
    }

    @GetMapping("/admin/dashboard")
    public String loadDashboard(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        return "admin/layout";
    }

    @GetMapping("/admin/order")
    public String loadBill(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/order/order.html");
        return "admin/layout";
    }

    @GetMapping("/admin/direct_sales")
    public String loadDirectSales(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/directSales.html");
        return "admin/page/directSales";
    }

    @GetMapping("/admin/customer")
    public String loadCustomer(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/customer/customer.html");
        return "admin/layout";
    }

    @GetMapping("/admin/staff")
    public String loadStaff(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/staff.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product")
    public String loadProduct(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/product/product.html");
        return "admin/layout";
    }

    @GetMapping("/admin/statistic")
    public String loadStatistic(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        return "admin/layout";
    }

}
