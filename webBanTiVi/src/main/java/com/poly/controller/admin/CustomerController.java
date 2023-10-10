package com.poly.controller.admin;

import com.poly.entity.Customer;
import com.poly.service.CustomerService;
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
    public String addCustomer(Customer customer) {
        this.customerService.save(customer);
        return "admin/layout";
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
    public String updateCustomer(@PathVariable("id") Integer id, @Valid Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            customer.setId(id);
            return "admin/layout";
        }
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
