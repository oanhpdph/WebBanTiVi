package com.poly.controller.admin;

import com.poly.entity.Product;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @RequestMapping("/product/save")
    public String saveProduct(Model model, @ModelAttribute("item")Product product) {
        productService.save(product);
        return "admin/product";
    }
    @RequestMapping("/product/delete/{id}")
    public String deleteProduct(Model model,@PathVariable("id")Integer id) {
        productService.delete(id);
        return "admin/product";
    }
    @RequestMapping("/product/edit/{id}")
    public String editProduct(Model model,@PathVariable("id")Integer id) {
        productService.findById(id);
        return "admin/product";
    }
    @GetMapping("/product")
    public String loadProduct(HttpSession session,Model model) {
        session.setAttribute("pageView", "/admin/page/product/product.html");
        session.setAttribute("active","/product");
        model.addAttribute("listsp",productService.findAll());
        return "admin/layout";
    }
}
