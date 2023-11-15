package com.poly.controller.admin;

import com.poly.entity.CouponProduct;
import com.poly.entity.idClass.CouponProductId;
import com.poly.service.Impl.CouponProductServiceImpl;
import com.poly.service.Impl.CouponServiceImpl;
import com.poly.service.ProductDetailService;
import jakarta.servlet.http.HttpSession;
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
    CouponProductServiceImpl couponProductService;
    @Autowired
    CouponServiceImpl couponService;
    @Autowired
    ProductDetailService productDetailService;
    @RequestMapping("/admin/couponproduct/{id}")
    public String CouponProduct(HttpSession session,Model model,@PathVariable("id")Integer id) {
        model.addAttribute("listCouponProduct",couponProductService.getAllCouponProductByCouponId(id));
        model.addAttribute("listproduct",productDetailService.findAll());
        model.addAttribute("discount",couponService.findById(id).get());
        session.setAttribute("pageView", "/admin/page/coupon/discountProduct.html");
        return "admin/layout";
    }
    @RequestMapping("/admin/couponproduct/save")
    public String saveCouponProduct(Model model, @ModelAttribute("item") CouponProduct couponProduct) {
        couponProductService.save(couponProduct);
        return "admin/coupon_product";
    }
    @RequestMapping("/admin/couponproduct/edit/{id}")
    public String editCouponProduct(Model model, @PathVariable("id") CouponProductId id) {
        couponProductService.findById(id);
        return "admin/coupon_product";
    }
    @RequestMapping("/admin/couponproduct/delete/{id}")
    public String deleteCouponProduct(Model model, @PathVariable("id")CouponProductId id) {
        couponProductService.delete(id);
        return "admin/coupon_product";
    }
}
