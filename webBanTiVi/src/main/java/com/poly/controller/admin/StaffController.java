package com.poly.controller.admin;

import com.poly.entity.Staff;
import com.poly.service.Impl.StaffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StaffController {
    @Autowired
    StaffServiceImpl staffService;

    @GetMapping("/staff/all")
    public String findAll(Model model){
       model.addAttribute("listStaff",staffService.findAll());
       return "admin/page/staff";
    }
}
