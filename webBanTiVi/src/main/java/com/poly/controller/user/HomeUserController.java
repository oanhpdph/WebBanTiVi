package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.ProductDetailDto;
import com.poly.entity.Product;
import com.poly.entity.ProductDetail;
import com.poly.service.CartService;
import com.poly.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Iterator;

@Controller
public class HomeUserController {
    @Autowired
    CartService cartService;

    @Autowired
    private CheckLogin checkLogin;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String loadHome(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/home/home.html");
        model.addAttribute("active", "home");
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setSort(1);
        productDetailDto.setSize(20);
        productDetailDto.setActive(true);

        productDetailDto.setGroup(1);

        Page<Product> page = productService.findAll(productDetailDto);
        for (Product product : page.getContent()) {
            Iterator<ProductDetail> iterator = product.getProductDetails().iterator();
            while (iterator.hasNext()) {
                ProductDetail productDetail = iterator.next();
                if (!productDetail.isActive()) {
                    iterator.remove();
                }
            }
        }
        model.addAttribute("listTivi", page);

        productDetailDto.setGroup(2);

        Page<Product> phuKien = productService.findAll(productDetailDto);

        for (Product product : phuKien.getContent()) {
            Iterator<ProductDetail> iterator = product.getProductDetails().iterator();
            while (iterator.hasNext()) {
                ProductDetail productDetail = iterator.next();
                if (!productDetail.isActive()) {
                    iterator.remove();
                }
            }
        }
//        UserDetailDto userDetailDto = checkLogin.checkLogin();
//        if (userDetailDto != null) {
//            Cart cart = cartService.getOneByUser(userDetailDto.getId());
//            session.setAttribute("list", cart.getListCartPro());
//        }
        model.addAttribute("listPhuKien", phuKien);
        return "/user/index";
    }
}
