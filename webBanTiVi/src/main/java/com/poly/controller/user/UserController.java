package com.poly.controller.user;

import com.poly.dto.ChangeInforDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping("/")
    public String loadHome(HttpSession session){
        session.setAttribute("pageView","/user/page/home/home.html");
        return "/user/index";
    }
    @GetMapping("/tivi")
    public String loadProduct(HttpSession session){
        session.setAttribute("pageView","/user/page/product/tivi.html");
        return "/user/index";
    }
    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session){
        session.setAttribute("pageView","/user/page/product/accessory.html");
        return "/user/index";
    }
    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session){
        session.setAttribute("pageView","/user/page/invoice/search_invoice.html");
        return "/user/index";
    }
    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String profile(HttpSession session, Model model){
        session.setAttribute("pageView","/user/page/profile/profile.html");
        model.addAttribute("changeInfo", new ChangeInforDto());
        return "/user/index";
    }
    @GetMapping("/invoice/invoice_detail")
    public String loadInvoiceDetail(HttpSession session){
        session.setAttribute("pageView","/user/page/invoice/detail_invoice.html");

        return "/user/index";
    }
}
