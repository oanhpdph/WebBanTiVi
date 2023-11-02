package com.poly.controller.admin;

import com.poly.dto.VoucherCustomerRes;
import com.poly.entity.VoucherCustomer;
import com.poly.service.CustomerService;
import com.poly.service.StaffService;
import com.poly.service.VoucherCustomerService;
import com.poly.service.VoucherService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class VoucherCustomerController {
    @Autowired
    StaffService staffService;

    @Autowired
    CustomerService customerService;


    @Autowired
    VoucherService voucherService;

    @Autowired
    VoucherCustomerService voucherCustomerService;


    // voucherCustomer
    @GetMapping("/voucherCustomer")
    public String voucherCustomer(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/voucher/voucherCustomer.html");
        session.setAttribute("active", "/voucherCustomer");
        session.setAttribute("listCustomer", this.customerService.findAll());
        session.setAttribute("listVoucher", this.voucherService.findAllList());
        model.addAttribute("listVoucherCustomer", this.voucherCustomerService.findAll());
        model.addAttribute("voucherCustomer", new VoucherCustomerRes());
        return "admin/layout";
    }

    @PostMapping("/voucherCustomer/add")
    public String addVoucherCustomer(Model model, @Valid @ModelAttribute("voucherCustomer") VoucherCustomerRes voucherCustomerRes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui long dien day du thong tin");
            return "admin/layout";
        }
        this.voucherCustomerService.save(voucherCustomerRes);
        model.addAttribute("listVoucherCustomer", this.voucherCustomerService.findAll());
        return "redirect:/admin/voucherCustomer";
    }


    @PostMapping("/voucherCustomer/update/{id}")
    public String updatevoucherCustomer(@PathVariable("id") Integer id, @Valid @ModelAttribute("voucherCustomer")  VoucherCustomerRes voucherCustomerRes, BindingResult result, Model model) {
        this.voucherCustomerService.save(voucherCustomerRes);
        return "redirect:/admin/voucherCustomer";
    }


}
