package com.poly.controller.admin;

import com.poly.entity.Brand;
import com.poly.service.Impl.BrandServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class BrandController {
    @Autowired
    BrandServiceImpl brandService;



    @GetMapping("/technical/brand")
    public String loadColor(HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/technical/brand.html");
        session.setAttribute("active", "/technical/brand");
        model.addAttribute("brand", new Brand());
        model.addAttribute("listBrand", this.brandService.getAll());
        return "admin/layout";
    }

    @PostMapping("/technical/brand/add")
    public String addColor(Model model, @Valid @ModelAttribute("brand") Brand brand, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            return "admin/layout";
        }
        this.brandService.add(brand);
        model.addAttribute("listBrand", brandService.getAll());
        return "redirect:/admin/technical/brand";
    }


    @GetMapping("/technical/brand/delete/{id}")
    public String deleteColor(@PathVariable("id") Integer id, Model model) {
        Brand brand = brandService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        brandService.delete(id);
        return "redirect:/admin/technical/brand";
    }

    @PostMapping("/technical/brand/update/{id}")
    public String updateColor(@PathVariable("id") Integer id,  @ModelAttribute("brand") Brand brand, Model model) {

        Brand findBrand = brandService.findById(brand.getId()).orElse(null);

        findBrand.setCode(brand.getCode());
        findBrand.setId(brand.getId());
        findBrand.setNameBrand(brand.getNameBrand());


        this.brandService.add(brand);
        model.addAttribute("listBrand", brandService.getAll());
        return "redirect:/admin/technical/brand";
    }





}
