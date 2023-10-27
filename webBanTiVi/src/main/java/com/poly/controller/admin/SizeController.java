package com.poly.controller.admin;


import com.poly.entity.Color;
import com.poly.entity.Size;

import com.poly.service.Impl.SizeServiceImpl;
import com.poly.service.SizeService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SizeController {
    @Autowired
    SizeServiceImpl sizeService;

    @GetMapping("/technical/size")
    public String loadSize(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/size.html");
        session.setAttribute("active", "/technical/size");
        model.addAttribute("size", new Size());
        model.addAttribute("listSize", this.sizeService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/size/add")
    public String addColor(Model model, @Valid @ModelAttribute("size") Size size, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.sizeService.add(size);
        model.addAttribute("listSize", sizeService.getAll());
        return "redirect:/admin/technical/size";
    }


    @GetMapping("/technical/size/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Size size = sizeService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        sizeService.delete(id);
        return "redirect:/admin/technical/size";
    }

}
