package com.poly.controller.admin;

import com.poly.dto.VoucherCustomerRes;
import com.poly.entity.VoucherCustomer;
import com.poly.entity.idClass.VoucherCustomerId;
import com.poly.service.CustomerService;
import com.poly.service.StaffService;
import com.poly.service.VoucherCustomerService;
import com.poly.service.VoucherService;
import jakarta.servlet.http.HttpSession;
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
//        session.setAttribute("listCustomer", this.customerService.findAll());
//        session.setAttribute("listVoucher", this.voucherService.findAll(pageable));
        model.addAttribute("listVoucherCustomer", this.voucherCustomerService.findAll());
        model.addAttribute("voucherCustomer", new VoucherCustomerRes());
        return "admin/layout";
    }

    @PostMapping("/voucherCustomer/add")
    public String addVoucherCustomer(Model model, @Valid VoucherCustomerRes voucherCustomerRes, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui long dien day du thong tin");
            return "admin/layout";
        }
        this.voucherCustomerService.save(voucherCustomerRes);
        model.addAttribute("listVoucherCustomer", this.voucherCustomerService.findAll());
        return "redirect:/admin/voucherCustomer";
    }

    @GetMapping("/voucherCustomer/edit/{id_voucher}/{id_customer}")
    public String editVoucherCustomer(@PathVariable("id_voucher") Integer id_voucher, @PathVariable("id_customer") Integer id_customer, Model model) {
        VoucherCustomerId voucherCustomerId = new VoucherCustomerId();
        voucherCustomerId.setCustomer(customerService.findById(id_customer).orElse(null));
        voucherCustomerId.setVoucher(voucherService.findById(id_voucher).orElse(null));
        VoucherCustomer voucher = voucherCustomerService.findById(voucherCustomerId)
                .orElse(null);
        model.addAttribute("listVoucherCustomer", voucherCustomerService.findAll());
        model.addAttribute("voucherCustomer", voucher);
        return "admin/layout";
    }

    @PostMapping("/voucherCustomer/update/{id}")
    public String updatevoucherCustomer(@PathVariable("id") Integer id, @Valid VoucherCustomerRes voucherCustomerRes, BindingResult result, Model model) {
        this.voucherCustomerService.save(voucherCustomerRes);
        return "redirect:/admin/voucherCustomer";
    }

//    @GetMapping("/voucherCustomer/delete/{id}")
//    public String deletevoucherCustomer(@PathVariable("id") Integer id, Model model) {
//        VoucherCustomer VoucherCustomer = this.voucherCustomerService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        this.voucherCustomerService.delete(id);
//        return "redirect:/admin/voucherCustomer";
//    }
}
