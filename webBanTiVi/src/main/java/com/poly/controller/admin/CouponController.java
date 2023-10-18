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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CouponController {
    @Autowired
    CouponServiceImpl couponService;
    @GetMapping("/coupon")
    public String showCouponList(Model model) {
        List<Coupon> listCoupon = couponService.findAll();
        return "coupon";
    }
    @GetMapping("/coupon/new")
    public String showNewForm(Model model){
        model.addAttribute("coupon",new Coupon());
        model.addAttribute("pageTitle","Add New Coupon");
        return "coupon_form";
    }
    @PostMapping("/coupon/save")
    public String saveCoupon(Coupon coupon, RedirectAttributes ra){
        couponService.save(coupon);
        ra.addFlashAttribute("message","Coupon has been saved successfully");
        return "redirect:/admin/coupon";
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
