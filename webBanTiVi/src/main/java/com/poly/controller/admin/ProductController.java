package com.poly.controller.admin;

import com.poly.dto.ProductDetailDto;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping("/product/list")
    public String getAll(@ModelAttribute(binding = false) ProductDetailDto detailDto, HttpSession session, Model model) {
        session.setAttribute("pageView", "/admin/page/product/list_product.html");
        session.setAttribute("active", "/product/list-product");
        model.addAttribute("listProductDetail",productService.findAll(detailDto));
        return "/admin/layout";
    }
}
