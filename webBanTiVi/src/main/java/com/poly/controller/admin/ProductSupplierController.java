package com.poly.controller.admin;

import com.poly.entity.ProductSupplier;
import com.poly.entity.idClass.ProductSupplierId;
import com.poly.service.Impl.ProductSupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductSupplierController {
    @Autowired
    ProductSupplierServiceImpl productsupplierService;
    @RequestMapping("/admin/product_supplier")
    public String Product_s(Model model) {
        productsupplierService.findAll();
        return "admin/product_supplier";
    }
    @RequestMapping("/admin/product_supplier/save")
    public String saveProduct_s(Model model, @ModelAttribute("item") ProductSupplier productSupplier) {
        productsupplierService.save(productSupplier);
        return "admin/product_supplier";
    }
    @RequestMapping("/admin/product_supplier/edit/{id}")
    public String editProduct_s(Model model, @PathVariable("id")ProductSupplierId id) {
        productsupplierService.findById(id);
        return "admin/product_supplier";
    }
    @RequestMapping("/admin/product_supplier/delete/{id}")
    public String deleteProduct_s(Model model, @PathVariable("id")ProductSupplierId id) {
        productsupplierService.delete(id);
        return "admin/product_supplier";
    }
}
