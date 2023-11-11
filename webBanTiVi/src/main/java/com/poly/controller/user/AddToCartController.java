package com.poly.controller.user;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.CartProduct;
import com.poly.entity.Users;
import com.poly.entity.Product;
import com.poly.entity.idClass.CartProductId;
import com.poly.service.CartProductService;
import com.poly.service.Impl.CPServiceImpl;
import com.poly.service.Impl.CartSeviceImpl;
import com.poly.service.Impl.UserServiceImpl;
import com.poly.service.Impl.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddToCartController {
    @Autowired
    private HttpSession session;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    UserServiceImpl customerService;
    @Autowired
    CartSeviceImpl cartSevice;
    @Autowired
    CartProductService cartProductService;
    @Autowired
    CPServiceImpl cpService;

    @GetMapping("/cart/add/{id}")
    public String themGioHang(@PathVariable("id") Integer id) {
        Product product = productService.getOne(id);

        if (product == null) {
            return "redirect:/error";
        }

        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            Users customer = customerService.getCustomerByName(username);
            var cart = cartSevice.getOne(customer.getId());
                cart.setCode(cart.getCode());
                cart.setDateUpdate(cart.getDateUpdate());
                CartProduct cartProduct = new CartProduct();
                cartProduct.setProduct(cartProduct.getProduct());
                cartProduct.setQuantity(1);
                cartProduct.setNote(cartProduct.getNote());
                cartProduct.setCart(cart);
//                cartProduct.setProduct(product);
                cartProductService.save(cartProduct);
            }

        return "redirect:/viewOrderCart";
    }
    @GetMapping("/cart/increase/{id}")
    public String IncreaseCart(@PathVariable("id") CartProductId id) {
        CartProduct cartProduct = cartProductService.getOne(id).get();
        Product product = productService.getOne(cartProduct.getProduct().getId());

        if (product == null) {

            return "redirect:/error";
        }

        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            Users customer = customerService.getCustomerByName(username);

            var cart = cartSevice.getOne(customer.getId());

            cart.setCode(cart.getCode());
            cartProduct.setQuantity(cartProduct.getQuantity() + 1);
            cartProduct.setCreateDate(cartProduct.getCreateDate());
        }
        return "redirect:/viewOrderCart";
    }
    @GetMapping("/cart/delete/{id}")
    public String deleteCart(@PathVariable("id") CartProductId id) {
        if (session.getAttribute("CustomerName") != null) {
            CartProduct cartProduct = cartProductService.getOne(id).get();

            if (cartProduct == null) {

                return "redirect:/error";
            }

            String username = (String) session.getAttribute("CustomerName");
            Users customer = customerService.getCustomerByName(username);

            var cart = cartSevice.getOne(customer.getId());

            cart.setCode(cart.getCode());
            cart.setDateUpdate(cart.getDateUpdate());
            cartProductService.delete(id);
        }
        return "redirect:/viewOrderCart";
    }
    @GetMapping("/viewOrderCart")
    public String showCartItem(Model model) {
        if (session.getAttribute("CustomerName") != null) {
            String username = (String) session.getAttribute("CustomerName");
            Users customer = customerService.getCustomerByName(username);
            model.addAttribute("cart", cartSevice.getOne(customer.getId()));
            model.addAttribute("listCartDetail", cartProductService.getAll());
            model.addAttribute("listProductDetail", productService.findAll(new ProductDetailDto()));
        }
        model.addAttribute("view", "/user/page/product/pro_cart.html");
        return "/user/index";
    }
}
