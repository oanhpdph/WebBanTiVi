package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.CartDto;
import com.poly.dto.EvaluateRes;
import com.poly.dto.UserDetailDto;
import com.poly.entity.CartProduct;
import com.poly.entity.Evaluate;
import com.poly.entity.ProductDetail;
import com.poly.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ProductDetailUserController {
    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    HttpSession session;

    @Autowired
    CartService cartService;

    @Autowired
    CartProductService cartProductService;

    @Autowired
    CustomerService customerService;

    @Autowired
    EvaluateService evaluateService;

    @Autowired
    ProductService productService;

    @Autowired
    private CheckLogin checkLogin;

    @GetMapping("/product/detail/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session) {
        ProductDetail product = productDetailService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("countEvaluate", product.getProduct().getListEvaluate().stream().filter(evaluate -> evaluate.isActive() == true).toList().size());

        model.addAttribute("productAll", product.getProduct());

        BigDecimal reduceMoney = BigDecimal.valueOf(0);
        if (product.getCoupon() != null && product.getCoupon().isActive() && (LocalDate.now().isAfter(product.getCoupon().getDateStart().toLocalDate()) && LocalDate.now().isBefore(product.getCoupon().getDateEnd().toLocalDate()))) {
            reduceMoney = product.getPriceExport().subtract(product.getPriceExport().multiply(BigDecimal.valueOf(Double.parseDouble(product.getCoupon().getValue())).divide(new BigDecimal(100))));
        }
        model.addAttribute("reduceMoney", reduceMoney);
        session.setAttribute("pageView", "/user/page/product/detail.html");
        model.addAttribute("listPro", this.productDetailService.findAll());
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        List<Evaluate> evaluates = product.getProduct().getListEvaluate();
        if (userDetailDto != null) {
            for (Evaluate evaluate : evaluates) {
                if (evaluate.getCustomer().getId() == userDetailDto.getId()) {
                    model.addAttribute("hasEvaluate", true);
                    break;
                }
                else {
                    model.addAttribute("hasEvaluate", false);
                }
            }
        }
        model.addAttribute("evaluate", evaluates.stream().filter(evaluate -> evaluate.isActive() == true).toList());
        return "user/index";
    }

    @PostMapping("/product/detail/{id}")
    public String add(@PathVariable Integer id, @RequestParam("qty") Integer qty, HttpSession session, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String url = request.getRequestURI();
        List<CartProduct> list = cartService.add(id, qty);
        session.setAttribute("list", list);
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            CartDto cartDto = new CartDto();
            cartDto.setIdProduct(id);
            cartDto.setIdUser(userDetailDto.getId());
            cartDto.setQuantity(qty);
            cartProductService.save(cartDto);
        }
        redirectAttributes.addFlashAttribute("message", true);
        return "redirect:" + url;
    }

    @GetMapping("/product/evaluate/{id}")
    public String index(@ModelAttribute(name = "evaluate") EvaluateRes evaluateDto, @PathVariable Integer id, Model model, HttpSession httpSession) {
        evaluateDto.setProduct(id);
        evaluateDto.setPage(1);
        evaluateDto.setSize(10000);
        Page<Evaluate> evaluates = evaluateService.getAll(evaluateDto);
        model.addAttribute("list", evaluates);
        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            for (Evaluate evaluate : evaluates.getContent()) {
                if (evaluate.getCustomer().getId() == userDetailDto.getId()) {
                    model.addAttribute("hasEvaluate", true);
                } else {
                    model.addAttribute("hasEvaluate", false);
                }
            }
        }
        httpSession.setAttribute("pageView", "/user/page/product/evaluate.html");
        model.addAttribute("product", productService.findById(id));
        return "user/index";
    }
}
