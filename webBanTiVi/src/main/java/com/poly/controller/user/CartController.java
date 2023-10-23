package com.poly.controller.user;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;
import com.poly.entity.Product;
import com.poly.service.CartProductService;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/user")
public class CartController {

    @Autowired
    HttpSession session;
    @Autowired
    CartSeviceImpl cartService;

    @Autowired
    ProductServiceImpl productService;

    @ModelAttribute("cart")
    CartSeviceImpl getCart() {
        return cartService;
    }

    @GetMapping("/cart")
    public String index(Model model) {
        session.setAttribute("pageView", "/admin/page/product/pro_cart.html");
        return "admin/layout";
    }

    @GetMapping("/detail")
    public String de(Model model) {
        session.setAttribute("pageView", "/admin/page/product/detail.html");
        return "admin/layout";
    }

    @RequestMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id, Date date) {
//        if (session.getAttribute("userCurrent") == null) {
//            return "redirect:/login";
//        }
        cartService.add(id, date);
        return "redirect:/cart";
    }

    @RequestMapping("/cart_add/{id}")
    public String addCart(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        Cart c = new Cart();
//        Date dateCreate =
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProduct(product);
        cartProduct.setCart(c);
        cartProduct.setQuantity(1);
        cartProduct.setCreateDate(cartProduct.getCreateDate());
        cartProduct.setDateUpdate(c.getDateUpdate());

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            Cart cart1 = new Cart();
            List<CartProduct> listcart = new ArrayList<>();
            listcart.add(cartProduct);
            cart1.setListCartPro(listcart);
            session.setAttribute("cart", cart1);
            System.out.println(cart1.getListCartPro().size());
            System.out.println("chay duoc den day la 1 nua r");
        } else {
            Boolean check = false;
            List<CartProduct> list = cart.getListCartPro();
            for (CartProduct x : list) {
                if (x.getProduct().getId() == id) {
                    check = true;
                    x.setQuantity(x.getQuantity() + 1);
                }
                if (check == false) {
                    list.add(cartProduct);
                }
                cart.setListCartPro(list);
                session.setAttribute("cart", cart);
                System.out.println("chay lan 2 den day la ok");
            }
        }
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

    @GetMapping("/cart/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        session.setAttribute("pageView", "/admin/page/product/detail.html");
        return "admin/layout";
    }

    @RequestMapping("/cart/update/{id}")
    public String update(@PathVariable Integer id, int qty) {
        cartService.update(id, qty);
        System.out.println("sua thanh cong");
        return "redirect:/cart";
    }
}
