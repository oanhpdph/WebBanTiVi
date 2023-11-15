//package com.poly.controller.user;
//
//import com.poly.entity.CartProduct;
//import com.poly.entity.idClass.CartProductId;
//import com.poly.service.Impl.CPServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class CartProductController {
//    @Autowired
//    CPServiceImpl service;
//
//    @GetMapping("/cart_product")
//    public String getAll(Model model) {
//        model.addAttribute("list", service.getAll());
//        return "";
//    }
//
//    @GetMapping("/cart_product/add")
//    public String add(CartProduct cp) {
//        service.save(cp);
//        return "redirect:/cart_product";
//    }
//
//    @GetMapping("/cart_product/delete/{id}")
//    public String delete(@PathVariable CartProductId id) {
//        service.delete(id);
//        return "redirect:/cart_product";
//    }
//
//    @GetMapping("/cart_product/edit/{id}")
//    public String edit(@PathVariable CartProductId id, Model model) {
//        CartProduct cart_product = service.edit(id);
//        model.addAttribute("cart_product", cart_product);
//        return "redirect:/cart_product";
//    }
//
//    @GetMapping("/cart_product/update/{id}")
//    public String update(@PathVariable Integer id, Model model, @ModelAttribute("cart_product") CartProduct cart_product) {
//        service.save(cart_product);
//        return "redirect:/cart_product";
//    }
////    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        if (!(authentication instanceof AnonymousAuthenticationToken)) {
////        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
////        UserDetailDto customerUserDetail = (UserDetailDto) userDetails;
//
//}
