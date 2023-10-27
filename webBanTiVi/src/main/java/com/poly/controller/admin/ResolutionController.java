package com.poly.controller.admin;

import com.poly.entity.Color;
import com.poly.entity.Resolution;
import com.poly.entity.Size;
import com.poly.service.Impl.ColorServiceImpl;
import com.poly.service.Impl.ResolutionServiceImpl;
import com.poly.service.ResolutionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ResolutionController {
    @Autowired
    ResolutionServiceImpl resolutionService;



    @GetMapping("/technical/resolution")
    public String loadColor(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/resolution.html");
        session.setAttribute("active", "/technical/resolution");
        model.addAttribute("resolution", new Resolution());
        model.addAttribute("listResolution", this.resolutionService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/resolution/add")
    public String addColor(Model model, @Valid @ModelAttribute("resolution") Resolution resolution, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.resolutionService.add(resolution);
        model.addAttribute("listResolution", resolutionService.getAll());
        return "redirect:/admin/technical/resolution";
    }


    @GetMapping("/technical/resolution/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Resolution resolution = resolutionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        resolutionService.delete(id);
        return "redirect:/admin/technical/resolution";
    }
}
