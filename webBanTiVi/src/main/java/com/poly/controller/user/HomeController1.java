package com.poly.controller.user;

import com.poly.service.Impl.CouponProductServiceImpl;
import com.poly.service.Impl.CouponServiceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController1 {
    @Autowired
    ProductServiceImpl productService;

    @Autowired
    CouponServiceImpl couponService;

    @Autowired
    CouponProductServiceImpl couponProductService;

    @GetMapping("/")
    public String loadHome(HttpSession session, Model model) {
//        List<Product> product = productService.findAll();
//        long millis = System.currentTimeMillis();
//        Date today = new Date(millis);
        model.addAttribute("product", productService.findAll());
        model.addAttribute("coupon",couponService.findAll());
//        model.addAttribute("getvalue",productService.getProducts());
//        System.out.println(productService.getProducts().get(0).getImageProduct().getNameImage());
        session.setAttribute("pageView", "/user/page/home/home.html");
        return "/user/index";
    }

    @GetMapping("/tivi")
    public String loadProduct(HttpSession session, Model model) {

        session.setAttribute("pageView", "/user/page/product/tivi.html");
//        List<ProductDto> productDTOs = productService.getProducts();
//        model.addAttribute("products", productDTOs);
        model.addAttribute("product", productService.findAll());
        return "/user/index";
    }

    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session) {
        session.setAttribute("pageView", "/user/page/product/accessory.html");
        return "/user/index";
    }

    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session) {
        session.setAttribute("pageView", "/user/page/invoice/search_invoice.html");
        return "/user/index";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        session.setAttribute("pageView", "/user/page/profile/profile.html");
        return "/user/index";
    }
}
