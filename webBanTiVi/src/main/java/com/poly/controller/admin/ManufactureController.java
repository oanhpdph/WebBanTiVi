package com.poly.controller.admin;


import com.poly.entity.Manufacture;
import com.poly.service.Impl.ManufactureServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/api/manufacture")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class ManufactureController {
    @Autowired
    ManufactureServiceImpl manufactureService;



    @GetMapping("/technical/manufacture")
    public String loadColor(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/manufacture.html");
        session.setAttribute("active", "/technical/manufacture");
        model.addAttribute("manufacture", new Manufacture());
        model.addAttribute("listManufacture", this.manufactureService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/manufacture/add")
    public String addColor(Model model, @Valid @ModelAttribute("manufacture") Manufacture manufacture, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.manufactureService.add(manufacture);
        model.addAttribute("listManufacture", manufactureService.getAll());
        return "redirect:/admin/technical/manufacture";
    }


    @GetMapping("/technical/manufacture/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Manufacture manufacture = manufactureService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        manufactureService.delete(id);
        return "redirect:/admin/technical/manufacture";
    }

    @PostMapping("/technical/manufacture/update/{id}")
    public String updateColor(@PathVariable("id") Integer id,  @ModelAttribute("manufacture") Manufacture manufacture, Model model) {

        Manufacture findManufacture = manufactureService.findById(manufacture.getId()).orElse(null);

        findManufacture.setCode(manufacture.getCode());
        findManufacture.setId(manufacture.getId());
        findManufacture.setNameManufacture(manufacture.getNameManufacture());



        this.manufactureService.add(manufacture);
        model.addAttribute("listManufacture", manufactureService.getAll());
        return "redirect:/admin/technical/manufacture";
    }
}
