package com.poly.controller.admin;

import com.poly.entity.Staff;
import com.poly.service.StaffService;
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
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/staff")
    public String loadStaff(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/staff.html");
        session.setAttribute("active", "/staff");
        model.addAttribute("listStaff",staffService.findAll());
        model.addAttribute("staff", new Staff());
        return "admin/layout";
    }

    @PostMapping("/staff/add")
    public String addStaff(Model model, @Valid Staff staff, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Vui long dien day du thong tin");
            return "admin/layout";
        }
        this.staffService.save(staff);
        model.addAttribute("listStaff", staffService.findAll());
        return "redirect:/admin/staff";
    }

    @GetMapping("/staff/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Staff staff = staffService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("listStaff", staffService.findAll());
        model.addAttribute("staff", staff);
        return "redirect:/admin/staff";
    }

    @PostMapping("/staff/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            staff.setId(id);
            return "admin/layout";
        }
        staffService.save(staff);
        return "redirect:/admin/staff";
    }

    @GetMapping("/staff/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        Staff staff = staffService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        staffService.delete(id);
        return "redirect:/admin/staff";
    }
}
