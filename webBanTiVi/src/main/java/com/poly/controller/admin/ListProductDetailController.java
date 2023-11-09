package com.poly.controller.admin;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.ProductDetail;
import com.poly.service.ProductDetailService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class ListProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/product/list-product")
    public String loadProduct(HttpSession session, Model model, @ModelAttribute(value = "productDetailDto",binding = false) ProductDetailDto productDetailDto,
                              @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                              @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
                              ) {
        session.setAttribute("pageView", "/admin/page/product/list_product.html");
        session.setAttribute("active", "/product/list-product");

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDetail> productDetailList = productDetailService.findAll(productDetailDto, pageable);
        model.addAttribute("listProductDetail", productDetailList);
        return "admin/layout";
    }
}
