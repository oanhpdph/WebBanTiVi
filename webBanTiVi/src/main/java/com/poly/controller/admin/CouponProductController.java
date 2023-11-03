package com.poly.controller.admin;

import com.poly.entity.CouponProduct;
import com.poly.entity.idClass.CouponProductId;
import com.poly.service.Impl.CouponProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
public class CouponProductController {
    @Autowired
    CouponProductServiceImpl productService;
    @RequestMapping("/admin/coupon_product")
    public String CouponProduct(Model model) {
        productService.findAll();
        return "admin/coupon_product";
    }
    @RequestMapping("/admin/coupon_product/save")
    public String saveCouponProduct(Model model, @ModelAttribute("item") CouponProduct couponProduct) {
        productService.save(couponProduct);
        return "admin/coupon_product";
    }
    @RequestMapping("/admin/coupon_product/edit/{id}")
    public String editCouponProduct(Model model, @PathVariable("id") CouponProductId id) {
        productService.findById(id);
        return "admin/coupon_product";
    }
    @RequestMapping("/admin/coupon_product/delete/{id}")
    public String deleteCouponProduct(Model model, @PathVariable("id")CouponProductId id) {
        productService.delete(id);
        return "admin/coupon_product";
    }
}
