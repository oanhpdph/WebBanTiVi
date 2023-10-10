package com.poly.controller.admin;

import com.poly.entity.Voucher;
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
public class VoucherController {

    @Autowired
    private VoucherService voucherService;
    // voucher
    @GetMapping("/voucher/list")
    public String voucher(HttpSession session, Model model){
        model.addAttribute("pageView", "/admin/page/voucher/voucher.html");
        model.addAttribute("active", "/voucher/list");
        model.addAttribute("listVoucher",this.voucherService.findAll());
        model.addAttribute("voucher",new Voucher());
        return "admin/layout";
    }
    @PostMapping("/voucher/add")
    public String addVoucher(Model model, @Valid Voucher voucher, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui long dien day du thong tin");
            return "admin/layout";
        }
        this.voucherService.save(voucher);
        model.addAttribute("listVoucher", voucherService.findAll());
        return "redirect:/admin/voucher";
    }

    @GetMapping("/voucher/edit/{id}")
    public String showVoucher(@PathVariable("id") Integer id, Model model) {
        Voucher voucher = this.voucherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("voucher", voucher);
        model.addAttribute("listVoucher", this.voucherService.findAll());
        return "admin/layout";
    }

    @PostMapping("/voucher/update/{id}")
    public String updatevoucher(@PathVariable("id") Integer id, @Valid Voucher voucher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            voucher.setId(id);
            return "admin/layout";
        }
        this.voucherService.save(voucher);
        return "redirect:/admin/voucher";
    }

    @GetMapping("/voucher/delete/{id}")
    public String deletevoucher(@PathVariable("id") Integer id, Model model) {
        Voucher voucher = this.voucherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        this.voucherService.delete(id);
        return "redirect:/admin/voucher";
    }
}
