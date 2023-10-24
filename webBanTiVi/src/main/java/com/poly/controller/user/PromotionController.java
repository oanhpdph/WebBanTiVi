package com.poly.controller.user;

import com.poly.service.Impl.CouponProductServiceImpl;
import com.poly.service.Impl.CouponServiceImpl;
import com.poly.service.Impl.VoucherCustomerServiceImpl;
import com.poly.service.Impl.VoucherServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromotionController {
    @Autowired
    VoucherServiceImpl voucherService;
    @Autowired
    VoucherCustomerServiceImpl voucherCustomerService;
    @Autowired
    CouponServiceImpl couponService;
    @Autowired
    CouponProductServiceImpl couponProductService;
    @GetMapping("/promotion")
    public String index(HttpSession session, Model model){
        model.addAttribute("Coupon",couponService.getAllCouponRes());
        session.setAttribute("pageView", "/user/page/promotions.html");
        return "user/layout";
    }
}
