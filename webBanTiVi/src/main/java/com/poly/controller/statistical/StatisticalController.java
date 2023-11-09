package com.poly.controller.statistical;

import com.poly.service.Impl.BillImpl;
import com.poly.service.Impl.BillProductServiceImpl;
import com.poly.service.Impl.BillStatusImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class StatisticalController {
    @Autowired
    BillImpl billService;
    @Autowired
    BillProductServiceImpl billProductService;
    @Autowired
    BillStatusImpl billStatus;
    public static class DateStatistic{
        int id;
    }

    @GetMapping("/dashboard")
    public String thongke(HttpSession session, Model model){
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
}
