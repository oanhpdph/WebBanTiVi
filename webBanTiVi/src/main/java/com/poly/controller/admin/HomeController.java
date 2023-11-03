package com.poly.controller.admin;

import com.poly.entity.Bill;
import com.poly.service.DashBoardService;
import com.poly.service.Impl.BillImpl;
import com.poly.service.Impl.ProductServiceImpl;
import com.poly.service.Impl.StaffServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class HomeController {

    @Autowired
    StaffServiceImpl staffService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    DashBoardService dashBoardService;

    @GetMapping("")
    public String loadHome(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active", "/dashboard");
//        List<Bill> billReturn=this.dashBoardService.getBillReturned(date);
//        List<Bill> billProcessing=this.dashBoardService.getBillByProccesing(date);
//        model.addAttribute("billReturn",billReturn);
//        model.addAttribute("billProcessing",billProcessing);
        return "admin/layout";
    }

    @GetMapping("/dashboard")
    public String loadDashboard(HttpSession session,Model model) {
        session.setAttribute("pageView", "/admin/page/dashboard/dashboard.html");
        session.setAttribute("active", "/dashboard");
        return "admin/layout";
    }

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
