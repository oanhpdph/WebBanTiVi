package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.CartDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.*;
import com.poly.service.CartProductService;
import com.poly.service.CartService;
import com.poly.service.CustomerService;
import com.poly.service.ProductDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductDetailUserController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    HttpSession session;

    @Autowired
    CartService cartService;

    @Autowired
    CartProductService cartProductService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private CheckLogin checkLogin;

    @GetMapping("/product/detail/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        ProductDetail product = productDetailService.findById(id);
        model.addAttribute("product", product);
        session.setAttribute("pageView", "/user/page/product/detail.html");
        model.addAttribute("listPro", this.productDetailService.findAll());
        return "user/index";
    }

    @PostMapping("/product/detail/{id}")
    public String add(@PathVariable Integer id, @RequestParam("qty") Integer qty, HttpSession session, Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        List<CartProduct> list = cartService.add(id, qty);
        session.setAttribute("list", list);
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            CartDto cartDto = new CartDto();
            cartDto.setIdProduct(id);
            cartDto.setIdUser(userDetailDto.getId());
            cartDto.setQuantity(qty);

            cartProductService.save(cartDto);

        }
        return "redirect:" + url;
    }

}
