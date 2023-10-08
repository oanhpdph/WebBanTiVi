package com.poly.controller.admin;

import com.poly.entity.Customer;
import com.poly.entity.Staff;
import com.poly.entity.Voucher;
import com.poly.entity.VoucherCustomer;
import com.poly.service.CustomerService;
import com.poly.service.StaffService;
import com.poly.service.VoucherCustomerService;
import com.poly.service.VoucherService;
import jakarta.servlet.http.HttpSession;
//import jakarta.validation.Valid;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {


    @GetMapping("")
    public String loadHome(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/order")
    public String loadBill(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/order/order.html");
        session.setAttribute("active", "/order");
        return "admin/layout";
    }

    @GetMapping("/direct_sales")
    public String loadDirectSales(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/direct_sales/directSales.html");
        return "admin/page/direct_sales/directSales";
    }


    @GetMapping("/position")
    public String loadPosition(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/position.html");
        session.setAttribute("active", "/position");
        return "admin/layout";
    }

    @GetMapping("/product")
    public String loadProduct(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/product/product.html");
        session.setAttribute("active", "/product");
        return "admin/layout";
    }

    @GetMapping("/statistic")
    public String loadStatistic(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/statistic.html");
        session.setAttribute("active", "/statistic");
        return "admin/layout";
    }

    @GetMapping("/blog")
    public String loadBlog(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/blog/blog.html");
        session.setAttribute("active", "/blog");
        return "admin/layout";
    }



}
