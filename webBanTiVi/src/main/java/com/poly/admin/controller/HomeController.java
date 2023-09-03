package com.poly.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/admin")
    public String loadHome(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard.html");
        return "admin/layout";
    }



    @GetMapping("/admin/dashboard")
    public String loadDashboard(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/dashboard.html");
        return "admin/layout";
    }

    @GetMapping("/admin/bill")
    public String loadBill(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/bill.html");
        return "admin/layout";
    }

    @GetMapping("/admin/direct_sales")
    public String loadDirectSales(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/directSales.html");

        return "admin/page/directSales";
    }

    @GetMapping("/admin/customer")
    public String loadCustomer(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/customer.html");
        return "admin/layout";
    }

    @GetMapping("/admin/staff")
    public String loadStaff(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/staff.html");
        return "admin/layout";
    }

    ///////
    @GetMapping("/admin/product")
    public String loadProduct(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/product.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/list")
    public String loadPro(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/list_product.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/brand")
    public String loadBrand(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/brand.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/guarantee")
    public String loadGuarantee(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/guarantee.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/origin")
    public String loadOrigin(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/origin.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/manufacturer")
    public String loadManufacturer(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/manufacturer.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/color")
    public String loadColor(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/color.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/category")
    public String loadCate(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/category.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/resolution")
    public String loadResolution(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/resolution.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/size")
    public String loadSize(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/size.html");
        return "admin/layout";
    }

    @GetMapping("/admin/product/image")
    public String loadImage(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/image.html");
        return "admin/layout";
    }

    @GetMapping("/admin/statistic")
    public String loadStatistic(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/bill.html");
        return "admin/layout";
    }

    @GetMapping("/Login")
    public String loadLogin(HttpSession session){
        session.setAttribute("pageView","/admin/page/dashboard.html");
        return "admin/layout";
    }
}
