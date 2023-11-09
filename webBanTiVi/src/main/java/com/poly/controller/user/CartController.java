package com.poly.controller.user;

import com.poly.dto.BillProRes;
import com.poly.entity.*;
import com.poly.repository.CartRepos;
import com.poly.service.BillService;
import com.poly.service.CustomerService;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

    @PostMapping("/create_bill")
    public String addBill(Model model,
                          String email,
                          Integer id,
                          @ModelAttribute("billProduct") BillProRes billProRes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        if (result.hasErrors()) {
//            return "user/index";
//        }
        Customer checkEmail = customerService.findByEmail(email);

        if (checkEmail == null) {
            customerService.add(billProRes);
            return "redirect:/pay";
        }
        billProRes.setCustomer(checkEmail);
        Bill bill1 = billService.add(billProRes);
        billService.addBillPro(bill1, billProRes);
        model.addAttribute("listCus", customerService.findAll());
        return "redirect:/confirm";

    }

    @GetMapping("/cart")
    public String index(Model model) {
        session.setAttribute("pageView", "/user/page/product/pro_cart.html");
        return "user/index";
    }


    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id, HttpSession session, Model model) {
        List<CartProduct> list = cartService.add(id);
        session.setAttribute("list", list);
        int amount = (int) cartService.getTotalProduct();
        model.addAttribute("amount", amount);
        return "redirect:/cart";
    }


    @RequestMapping("/cart/delete/{id}")
    public String delete(@PathVariable int id) {
        cartService.delete(id);
        if (cartService.getTotal() == 0) {
            return "redirect:/";
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/detail/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        session.setAttribute("pageView", "/user/page/product/detail.html");
        model.addAttribute("listPro", this.productService.findAll());
        return "user/index";
    }

    @PostMapping("/cart/update")
    public String update(@RequestParam(value = "id", required = false) List<Integer> id, @RequestParam("qty") List<Integer> qty, Model model) {

        List<CartProduct> list = new ArrayList<>();
        for (int i = 0; i < id.size(); i++) {
            list = cartService.update(id.get(i), qty.get(i));
        }

        session.setAttribute("list", list);
        int total = (int) cartService.getTotalProduct();
        model.addAttribute("total", total);
        return "redirect:/cart";
    }

    @PostMapping("/purchase")
    public String pur(Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        cartRepos.save(cart);
        session.removeAttribute("cart");
        return "redirect:/cart";
    }
}
