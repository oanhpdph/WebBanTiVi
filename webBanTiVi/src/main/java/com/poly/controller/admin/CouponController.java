package com.poly.controller.admin;

import com.poly.entity.Coupon;
import com.poly.service.Impl.CouponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CouponController {
    @Autowired
    CouponServiceImpl couponService;
    @RequestMapping("/admin/coupon")
    public String Coupon(Model model) {
        couponService.findAll();
        return "admin/coupon";
    }
    @RequestMapping("/admin/coupon/save")
    public String saveCoupon(Model model, @ModelAttribute("item") Coupon coupon) {
        couponService.save(coupon);
        return "admin/coupon";
    }

    @RequestMapping("/admin/coupon/edit/{id}")
    public String editCoupon(Model model, @PathVariable("id")Integer id) {
        couponService.findById(id);
        return "admin/coupon";
    }
    @RequestMapping("/admin/coupon/delete/{id}")
    public String deleteCoupon(Model model, @PathVariable("id")Integer id) {
        couponService.delete(id);
        return "admin/coupon";
    }
}
