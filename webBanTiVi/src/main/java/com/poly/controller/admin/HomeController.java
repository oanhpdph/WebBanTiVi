package com.poly.controller.admin;

import com.poly.service.Impl.BillImpl;
import com.poly.service.Impl.ProductServiceImpl;
import com.poly.service.Impl.StaffServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    StaffServiceImpl staffService;

    @Autowired
    ProductServiceImpl productService;

    @GetMapping("")
    public String loadHome(HttpSession session,Model model) {
        model.addAttribute("pageView", "/admin/page/statistic.html");
        model.addAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session,Model model) {
        model.addAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        model.addAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/position")
    public String loadPosition(HttpSession session,Model model) {
        model.addAttribute("pageView", "/admin/page/position.html");
        model.addAttribute("active", "/position");
        return "admin/layout";
    }
   //product


    @GetMapping("/statistic")
    public String loadStatistic(HttpSession session, Model model) {
        model.addAttribute("pageView", "/admin/page/statistic.html");
        model.addAttribute("active", "/statistic");
        return "admin/layout";
    }

    @GetMapping("/blog")
    public String loadBlog(HttpSession session, Model model) {
        model.addAttribute("pageView", "/admin/page/blog/blog.html");
        model.addAttribute("active", "/blog");
        return "admin/layout";
    }

}
