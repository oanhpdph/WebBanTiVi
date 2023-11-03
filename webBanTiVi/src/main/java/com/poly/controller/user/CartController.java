package com.poly.controller.user;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;
import com.poly.entity.Product;
import com.poly.repository.CartRepos;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    ProductServiceImpl productService;

    @ModelAttribute("cart")
    CartSeviceImpl getCart() {
        return cartService;
    }

    @GetMapping("/pay")
    public String pay(Model model) {
        session.setAttribute("pageView", "/admin/page/product/pay.html");
        return "admin/layout";
    }

    @GetMapping("/cart")
    public String index(Model model) {
        session.setAttribute("pageView", "/admin/page/product/pro_cart.html");
        return "admin/layout";
    }


    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id, HttpSession session) {
        List<CartProduct> list = cartService.add(id);
        session.setAttribute("list", list);
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
        session.setAttribute("pageView", "/admin/page/product/detail.html");
        model.addAttribute("listPro", this.productService.findAll());
        return "admin/layout";
    }

    @PostMapping("/cart/update/{id}")
    public String update(@PathVariable Integer id, Integer qty, Model model) {
        cartService.update(id, qty);
//        session.setAttribute("list", list);
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
