package com.poly.controller.admin;

import com.poly.entity.Brand;



import com.poly.service.Impl.BrandServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class BrandController {
    @Autowired
    BrandServiceImpl brandService;


    @PostMapping("/brand/add")
    public ResponseEntity<?> add(@RequestBody @Valid Brand brand, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(brandService.add(brand), HttpStatus.OK);
    }

    @DeleteMapping("/brand/delete/{id}")
    public void delete(@PathVariable Integer id) {

        brandService.delete(id);
    }

    @PutMapping("/brand/update/{id}")
    public Brand update(@RequestBody Brand brand) {

        return brandService.add(brand);
    }
    @GetMapping ("/brand")
    public String Brand(Model model, HttpSession httpSession) {
        httpSession.setAttribute("pageView", "/admin/page/brand/brand.html");
        httpSession.setAttribute("active","/brand");
        model.addAttribute("listbr",brandService.getAll());
        return "admin/layout";
    }
}
