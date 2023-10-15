package com.poly.controller.admin;

import com.poly.entity.Customer;
import com.poly.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/list")
    public String loadCustomer(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/customer/customer.html");
        session.setAttribute("active", "/customer/list");
        model.addAttribute("customer", new Customer());
        model.addAttribute("listCus", this.customerService.findAll());
        return "admin/layout";
    }

    @PostMapping("/customer/save")
    public String addCustomer(@ModelAttribute("customer") Customer customer,Model model, @RequestParam("avatar") String file ) {
        customer.setAvatar(file);
        this.customerService.save(customer);
        model.addAttribute("listCus", customerService.findAll());
        return "redirect:/admin/customer/list";
    }

    @GetMapping("/customer/edit/{id}")
    public String showUpdateCustomer(@PathVariable("id") Integer id, Model model) {
        Customer cus = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("customer", cus);
        model.addAttribute("listCus", customerService.findAll());
        return "admin/layout";
    }

    @PostMapping("/customer/update/{id}")
    public String updateCustomer(@PathVariable("id") Integer id,
                              Customer customer,
                                 @RequestParam("avatar") String file) {
        customer.setAvatar(file);
        customerService.save(customer);
        return "redirect:/admin/customer/list";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        customerService.deleteById(id);
        return "redirect:/admin/customer/list";
    }
}
