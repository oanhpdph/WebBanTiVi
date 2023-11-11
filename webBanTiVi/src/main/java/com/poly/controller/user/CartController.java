package com.poly.controller.user;

import com.poly.dto.BillProRes;
import com.poly.entity.Bill;
import com.poly.entity.CartProduct;
import com.poly.entity.ProductDetail;
import com.poly.entity.Users;
import com.poly.repository.CartRepos;
import com.poly.service.BillService;
import com.poly.service.CustomerService;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import com.poly.service.ProductDetailService;
import jakarta.servlet.http.HttpServletRequest;
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

@Controller
//@RequestMapping("/user")
public class CartController {

    @Autowired
    HttpSession session;

    @Autowired
    CartRepos cartRepos;

    @Autowired
    CartSeviceImpl cartService;

    @Autowired
    CustomerService customerService;

    @Autowired
    BillService billService;

    @Autowired
    ProductServiceImpl productService;

    @Autowired
    private ProductDetailService productDetailService;

    @ModelAttribute("cart")
    CartSeviceImpl getCart() {
        return cartService;
    }

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
//        if (result.hasErrors()) {
//            return "user/index";
//        }
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
        return "user/index";
    }


    @PostMapping("/product/detail/{id}")
    public String add(@PathVariable Integer id, @RequestParam("qty") Integer qty, HttpSession session, Model model, HttpServletRequest request) {
        String url = request.getRequestURI();
        List<CartProduct> list = cartService.add(id, qty);
        session.setAttribute("list", list);
        return "redirect:" + url;
    }


    @RequestMapping("/cart/remove/{id}")
    public String delete(@PathVariable List<Integer> id) {

        List<CartProduct> list = new ArrayList<>();
//        List<CartProduct> list = cartService.add(id);
//        cartService.delete(id);
        for (int i = 0; i < id.size(); i++) {
            list = cartService.delete(id.get(i));
        }
        session.setAttribute("list", list);
        if (cartService.getTotal() == 0) {
            return "redirect:/";
        }
        return "redirect:/cart";
    }

    @GetMapping("/product/detail/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        ProductDetail product = productDetailService.findById(id);
        model.addAttribute("product", product);
        session.setAttribute("pageView", "/user/page/product/detail.html");
        model.addAttribute("listPro", this.productDetailService.findAll());
        return "user/index";
    }

    @PostMapping("/cart/update")
    public String update(@RequestParam(value = "id", required = false) List<Integer> id, @RequestParam("qty") List<Integer> qty, Model model) {

        List<CartProduct> list = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            list = cartService.update(id.get(i), qty.get(i));
        }
        session.setAttribute("list", list);
//        int total = (int) cartService.getTotalProduct();
//        model.addAttribute("total", total);
        return "redirect:/cart";
    }

//    @PostMapping("/purchase")
//    public String pur(Model model) {
//        Cart cart = (Cart) session.getAttribute("cart");
//        cartRepos.save(cart);
//        session.removeAttribute("cart");
//        return "redirect:/cart";
//    }
}
