package com.poly.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BillController {

    @GetMapping("/admin/order/{billCode}")
    public String loadDetailBill(HttpSession session, @PathVariable(name = "billCode") Integer billCode){
        session.setAttribute("pageView","/admin/page/bill/bill-detail.html");
        return "/admin/layout";
    }
}
