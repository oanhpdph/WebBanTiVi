package com.poly.controller.admin;

import com.poly.entity.Coupon;
import com.poly.entity.Product;
import com.poly.entity.Voucher;
import com.poly.service.Impl.CouponServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class CouponController {
    @Autowired
    CouponServiceImpl couponService;
    @RequestMapping("/coupon/save")
    public String saveCoupon(Model model, @ModelAttribute("item") Coupon coupon) {
        couponService.save(coupon);
        return "admin/coupon";
    }

    @RequestMapping("/coupon/edit/{id}")
    public String editCoupon(Model model, @PathVariable("id")Integer id) {
        couponService.findById(id);
        return "admin/coupon";
    }
    @RequestMapping("/coupon/delete/{id}")
    public String deleteCoupon(Model model, @PathVariable("id")Integer id) {
        couponService.delete(id);
        return "admin/coupon";
    }
    @GetMapping ("/coupon")
    public String Coupon(Model model, HttpSession httpSession) {
        httpSession.setAttribute("pageView", "/admin/page/coupon/coupon.html");
        httpSession.setAttribute("active","/coupon");
        model.addAttribute("listCoupon",couponService.findAll());
        return "admin/layout";
    }

}
