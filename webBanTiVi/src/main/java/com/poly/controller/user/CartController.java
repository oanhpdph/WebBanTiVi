package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.BillProRes;
import com.poly.dto.UserDetailDto;
import com.poly.entity.*;
import com.poly.entity.idClass.CartProductId;
import com.poly.repository.CartRepos;
import com.poly.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/user")
public class CartController {

    @Autowired
    HttpSession session;

    @Autowired
    CartRepos cartRepos;

    @Autowired
    CartService cartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BillService billService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CheckLogin checkLogin;


    @GetMapping("/pay")
    public String pay(Model model) {
        session.setAttribute("pageView", "/user/page/product/pay.html");
        return "user/index";
    }

    @GetMapping("/confirm")
    public String con(Model model) {
        session.setAttribute("pageView", "/user/page/product/confirm.html");
        return "user/index";
    }

    @PostMapping("/purchase")
    public String addBill(Model model,
                          String email,
                          Integer id,
                          @ModelAttribute("billProduct") BillProRes billProRes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Users checkEmail = customerService.findByEmail(email);

        if (checkEmail == null) {
            checkEmail = customerService.add(billProRes);
//            return "redirect:/pay";
        }

        billProRes.setCustomer(checkEmail);
        Bill bill1 = billService.add(billProRes);
        billProRes.setProduct(Collections.singletonList(id));
        billService.addBillPro(bill1, billProRes);
        model.addAttribute("listCus", customerService.findAll());
        return "redirect:/confirm";

    }

    @GetMapping("/cart")
    public String index(Model model) {
        session.setAttribute("pageView", "/user/page/product/pro_cart.html");
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            Cart cart = cartService.getOneByUser(userDetailDto.getId());
            session.setAttribute("list", cart.getListCartPro());
        }
        return "user/index";
    }

    @RequestMapping("/cart/remove/{id}")
    public String delete(@PathVariable List<Integer> id) {
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            Cart cart = cartService.getOneByUser(userDetailDto.getId());
            session.setAttribute("list", cart.getListCartPro());
            ProductDetail productDetail = productDetailService.findById(id.get(0));
            CartProductId cartProductId = new CartProductId();
            if (productDetail != null) {
                cartProductId.setProduct(productDetail);
                cartProductId.setCart(cart);
            }
            boolean check = cartProductService.delete(cartProductId);
        } else {
            List<CartProduct> list = new ArrayList<>();
            for (int i = 0; i < id.size(); i++) {
                list = cartService.delete(id.get(i));
            }
            session.setAttribute("list", list);
            if (cartService.getTotal() == 0) {
                return "redirect:/";
            }
        }
        return "redirect:/cart";
    }


    @PostMapping("/cart/update")
    public String update(@RequestParam(value = "id", required = false) List<Integer> id, @RequestParam("qty") List<Integer> qty, Model model) {

        UserDetailDto userDetailDto = checkLogin.checkLogin();

        if (userDetailDto != null) {
            Cart cart = cartService.getOneByUser(userDetailDto.getId());

            List<CartProduct> list = new ArrayList<>();

            for (int i = 0; i < id.size(); i++) {
                ProductDetail productDetail = productDetailService.findById(id.get(i));
                CartProductId cartProductId = new CartProductId();
                cartProductId.setCart(cart);
                cartProductId.setProduct(productDetail);
                Optional<CartProduct> optional = cartProductService.getOne(cartProductId);
                if (optional.isPresent()) {
                    CartProduct cartProduct = optional.get();
                    cartProduct.setQuantity(qty.get(i));
                    list.add(cartProductService.update(cartProduct));
                }
            }
            session.setAttribute("list", list);
        } else {
            List<CartProduct> list = new ArrayList<>();
            for (int i = 0; i < id.size(); i++) {
                list = cartService.update(id.get(i), qty.get(i));
            }
            session.setAttribute("list", list);
        }
        return "redirect:/cart";
    }
}
