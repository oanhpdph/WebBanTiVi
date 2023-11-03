package com.poly.controller.admin;

import com.poly.entity.ProductFeature;
import com.poly.entity.idClass.ProductFeatureId;
import com.poly.service.Impl.ProductFeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class ProductFeatureController {
    @Autowired
    ProductFeatureServiceImpl productFeatureService;
    @RequestMapping("/product_feature")
    public String ProductFeature(Model model) {
        productFeatureService.findAll();
        return "admin/product_feature";
    }
    @RequestMapping("/product_feature/save")
    public String saveProductFeature(Model model, @ModelAttribute("item") ProductFeature productFeature) {
        productFeatureService.save(productFeature);
        return "admin/product_feature";
    }
    @RequestMapping("/product_feature/edit/{id}")
    public String editProductFeature(Model model, @PathVariable("id") ProductFeatureId id) {
        productFeatureService.findById(id);
        return "admin/product_feature";
    }
    @RequestMapping("/product_feature/delete/{id}")
    public String deleteProductFeature(Model model, @PathVariable("id")ProductFeatureId id) {
        productFeatureService.delete(id);
        return "admin/product_feature";
    }
}
