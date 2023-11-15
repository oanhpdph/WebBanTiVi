package com.poly.controller.user;

import com.poly.dto.BillProRes;
import com.poly.entity.*;
import com.poly.repository.BillRepos;
import com.poly.repository.CartRepos;
import com.poly.service.BillService;
import com.poly.service.CustomerService;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.DeliveryNotesImpl;
import com.poly.service.Impl.ProductServiceImpl;
import com.poly.service.ProductDetailService;
import com.poly.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
    BillRepos billRepos;

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

    @Autowired
    private VNPayService vnPayService;

    @Autowired
    private DeliveryNotesImpl deliveryNotes;

    @ModelAttribute("cart")
    CartSeviceImpl getCart() {
        return cartService;
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        int total = cartService.getTotalProduct();
        model.addAttribute("total", total);
        session.setAttribute("pageView", "/user/page/product/pay.html");
        return "user/index";
    }

    @GetMapping("/confirm")
    public String con(Model model) {
        session.setAttribute("pageView", "/user/page/product/confirm.html");
        return "user/index";
    }

    @PostMapping("/purchase")
    public String addBill(BigDecimal orderTotal,
                          String orderInfo,
                          HttpServletRequest request,
                          Model model,
                          String email,
                          Integer id,
                          @ModelAttribute("billProduct") BillProRes billProRes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Users checkEmail = customerService.findByEmail(email);

        if (checkEmail == null) {
            checkEmail = customerService.add(billProRes);
        }
        int total = cartService.getTotalProduct();
        billProRes.setCustomer(checkEmail);
        Bill bill1 = billService.add(billProRes);
        billProRes.setBill(bill1);
        DeliveryNotes notes = deliveryNotes.save(billProRes);
        if (billProRes.getPaymentMethod() == 2) {
            //VNPAY
            String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            String vnpayUrl = vnPayService.createOrder(total, bill1.getCode(), baseUrl);
            cartService.clear();
            return "redirect:" + vnpayUrl;
        } else {
            billProRes.setProduct(Collections.singletonList(id));
            billService.addBillPro(bill1, billProRes);
            cartService.clear();
            return "redirect:/confirm";
        }

    }

    @GetMapping("/cart")
    public String index(Model model) {
        if (cartService.getTotal() == 0) {
            session.setAttribute("pageView", "/user/page/product/cart_null.html");
            return "user/index";
        }
        int total = cartService.getTotalProduct();
        model.addAttribute("total", total);
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
        for (int i = 0; i < id.size(); i++) {
            list = cartService.delete(id.get(i));
        }
        session.setAttribute("list", list);
        if (cartService.getTotal() == 0) {
            session.setAttribute("pageView", "/user/page/product/cart_null.html");
            return "user/index";
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

}
