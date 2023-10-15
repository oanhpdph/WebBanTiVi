package com.poly.controller.admin;

import com.poly.entity.Staff;
import com.poly.service.StaffService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin")
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/staff")
    public String loadStaff(HttpSession session, Model model)  {

        session.setAttribute("pageView", "/admin/page/staff.html");
        session.setAttribute("active", "/staff");
        model.addAttribute("listStaff",staffService.findAll());
        model.addAttribute("staff", new Staff());
        return "admin/layout";
    }


    @PostMapping("/staff/add")
    public String addStaff(Model model,  @ModelAttribute("staff") Staff staff, @RequestParam("avatar") String file)  {
        staff.setAvatar(file);
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
    public String updateUser(@PathVariable("id") Integer id, Staff staff, Model model, @RequestParam("avatar") String file) {
            
            staff.setAvatar(file);
           this.staffService.save(staff);


        model.addAttribute("listStaff", staffService.findAll());
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
