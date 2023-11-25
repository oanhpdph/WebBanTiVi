package com.poly.controller.admin;

import com.poly.entity.Brand;
import com.poly.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/brand/all")
    public ResponseEntity<?> findAllBrand() {
        List<Brand> list = brandService.findAll().stream().filter(brand -> brand.isActive() == true).toList();
        return ResponseEntity.ok(list);
    }
}
