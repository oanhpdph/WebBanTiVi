package com.poly.controller.admin;

import com.poly.entity.Color;
import com.poly.service.Impl.ColorServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    ColorServiceImpl colorService;



    @GetMapping("/technical/color")
    public String loadColor(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/color.html");
        session.setAttribute("active", "/technical/color");
        model.addAttribute("color", new Color());
        model.addAttribute("listColor", this.colorService.findAll());
        return "admin/layout";
    }

    @PostMapping("/technical/color/add")
    public String addColor(Model model, @Valid @ModelAttribute("color") Color color, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.colorService.add(color);
        model.addAttribute("listColor", colorService.findAll());
        return "redirect:/admin/technical/color";
    }


    @GetMapping("/technical/color/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Color color = colorService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        colorService.delete(id);
        return "redirect:/admin/technical/color";
    }

    @PostMapping("/technical/color/update/{id}")
    public String updateColor(@PathVariable("id") Integer id,  @ModelAttribute("color") Color color, Model model) {

        Color findColor = colorService.findById(color.getId()).orElse(null);

        findColor.setCode(color.getCode());
        findColor.setId(color.getId());
        findColor.setNameColor(color.getNameColor());


        this.colorService.add(color);
        model.addAttribute("listColor", colorService.findAll());
        return "redirect:/admin/technical/color";
    }



//    @GetMapping("/technical/color/edit/{id}")
//    public String updateColor(@PathVariable("id") Integer id, Model model) {
//        Color color = colorService.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("color", color);
//        model.addAttribute("listColor", colorService.findAll());
//        return "admin/layout";
//    }

}
