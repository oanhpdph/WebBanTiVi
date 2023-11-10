package com.poly.controller.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class BillReturnController {
    @GetMapping("/bill/list_invoice_return")
    public String getListInvoice(HttpSession session){
        session.setAttribute("pageView", "/admin/page/bill/invoice_return.html");
        session.setAttribute("active", "/bill/invoice_return");
        return "admin/layout";
    }
}
