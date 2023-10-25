package com.poly.controller.admin;

import com.poly.common.UploadFile;
import com.poly.entity.Staff;
import com.poly.service.StaffService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Controller
@RequestMapping("/admin")
public class StaffController {

    @Autowired
    StaffService staffService;


    @GetMapping("/staff")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    public String loadStaff(HttpSession session, Model model)  {

        session.setAttribute("pageView", "/admin/page/staff/staff.html");
        session.setAttribute("active", "/staff");
        model.addAttribute("listStaff",staffService.findAll());
        model.addAttribute("staff", new Staff());
        return "admin/layout";
    }


    @PostMapping("/staff/add")
    @PreAuthorize("hasAuthority('admin')")
    public String addStaff(Model model, @Valid @ModelAttribute("staff") Staff staff,BindingResult bindingResult, @RequestParam("image") MultipartFile file)  {

        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // xóa ký tự đặc biệt
        staff.setAvatar(fileName);
        this.staffService.save(staff);
        String uploadDir = "src/main/resources/static/image"; // đường dẫn upload
        try {
            UploadFile.saveFile(uploadDir, fileName, file);
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
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
    public String updateUser(@PathVariable("id") Integer id,  @ModelAttribute("staff") Staff staff, Model model, @RequestParam("image") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename()); // xóa ký tự đặc biệt
        Staff findStaff = staffService.findById(staff.getId()).orElse(null);

        findStaff.setUsername(staff.getUsername());
        findStaff.setName(staff.getName());
        findStaff.setEmail(staff.getEmail());
        findStaff.setGender(staff.isGender());
        findStaff.setPassword(staff.getPassword());
        findStaff.setPhone(staff.getPhone());
        findStaff.setAddress(staff.getAddress());
        findStaff.setRole(staff.getRole());
        findStaff.setBirthday(staff.getBirthday());
        if (!"".equals(fileName)) {
            findStaff.setAvatar(fileName);
            String uploadDir = "src/main/resources/static/image"; // đường dẫn upload
            try {
                UploadFile.saveFile(uploadDir, fileName, file);
            } catch (IOException e) {
                //
                e.printStackTrace();
            }
        }

           this.staffService.save(findStaff);
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
