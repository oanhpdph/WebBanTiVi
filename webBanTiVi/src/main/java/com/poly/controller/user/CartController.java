package com.poly.controller.user;

import com.poly.entity.Cart;
import com.poly.service.CartProductService;
import com.poly.service.Impl.CartSeviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    CartSeviceImpl cartService;

    @Autowired
    CartProductService cpService;

    @GetMapping("/cart/index")
    public String index(Model model) {
        model.addAttribute("list", cartService.getAll());
        return "user/page/shoppingcart";
    }

    @PostMapping("/cart/add")
    public String add(Cart cart) {
        cartService.add(cart);
        return "redirect:/cart/index";
    }

    @GetMapping("/cart/delete/{id}")
    public String delete(@PathVariable Integer id) {
        cartService.delete(id);
        return "redirect:/cart/index";
    }

    @GetMapping("/cart/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Cart cart = cartService.edit(id);
        model.addAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    @PostMapping("/cart/update/{id}")
    public String updateAccount(@PathVariable Integer id, @ModelAttribute("cart") Cart cart, Model model) {
        cartService.add(cart);
        System.out.println("sua thanh cong");
        return "redirect:/cart/index";
    }
}
