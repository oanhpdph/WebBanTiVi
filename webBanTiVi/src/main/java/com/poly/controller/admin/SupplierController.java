package com.poly.controller.admin;

import com.poly.entity.Product;
import com.poly.entity.Supplier;
import com.poly.service.Impl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SupplierController {
    @Autowired
    SupplierServiceImpl supplierService;
    @RequestMapping("/admin/supplier")
    public String Supplier(Model model) {
        supplierService.findAll();
        return "admin/supplier";
    }
    @RequestMapping("/admin/supplier/save")
    public String saveSupplier(Model model, @ModelAttribute("item") Supplier supplier) {
        supplierService.save(supplier);
        return "admin/supplier";
    }

    @RequestMapping("/admin/supplier/edit/{id}")
    public String editSupplier(Model model, @PathVariable("id")Integer id) {
        supplierService.findById(id);
        return "admin/supplier";
    }
    @RequestMapping("/admin/supplier/delete/{id}")
    public String deleteSupplier(Model model, @PathVariable("id")Integer id) {
        supplierService.delete(id);
        return "admin/supplier";
    }
}
