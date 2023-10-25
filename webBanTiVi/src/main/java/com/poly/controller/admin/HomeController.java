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
        session.setAttribute("pageView", "/admin/page/statistic/statistic.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session,Model model) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }
//
//    @GetMapping("/position")
//    public String loadPosition(HttpSession session,Model model) {
//        session.setAttribute("pageView", "/admin/page/position.html");
//        session.setAttribute("active", "/position");
//        return "admin/layout";
//    }
   //product


    @GetMapping("/statistic")
    public String loadStatistic(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/statistic/statistic.html");
        session.setAttribute("active", "/statistic");
        return "admin/layout";
    }

    @GetMapping("/blog")
    public String loadBlog(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/blog/blog.html");
        session.setAttribute("active", "/blog");
        return "admin/layout";
    }

}
