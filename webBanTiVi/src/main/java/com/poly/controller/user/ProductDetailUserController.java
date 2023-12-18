package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.CartDto;
import com.poly.dto.EvaluateRes;
import com.poly.dto.UserDetailDto;
import com.poly.entity.*;
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
import java.util.ArrayList;
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
    private BillService billService;

    @Autowired
    private CheckLogin checkLogin;

    @GetMapping("/product/detail/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpSession session) {
        ProductDetail product = productDetailService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("countEvaluate", product.getProduct().getListEvaluate().stream().filter(evaluate -> evaluate.isActive() == true).toList().size());

        model.addAttribute("productAll", product.getProduct());

        BigDecimal reduceMoney = BigDecimal.valueOf(0);
        if (product.getCoupon() != null && product.getCoupon().isActive()
                && ((LocalDate.now().isAfter(product.getCoupon().getDateStart().toLocalDate()) || (LocalDate.now().isEqual(product.getCoupon().getDateStart().toLocalDate())))
                && ((LocalDate.now().isBefore(product.getCoupon().getDateEnd().toLocalDate()) || LocalDate.now().isEqual(product.getCoupon().getDateEnd().toLocalDate()))))) {
            reduceMoney = product.getPriceExport().subtract(product.getPriceExport().multiply(BigDecimal.valueOf(Double.parseDouble(product.getCoupon().getValue())).divide(new BigDecimal(100))));
        }
        model.addAttribute("reduceMoney", reduceMoney);
        session.setAttribute("pageView", "/user/page/product/detail.html");
        model.addAttribute("listPro", this.productDetailService.findAll());
        UserDetailDto userDetailDto = checkLogin.checkLogin();

        List<Evaluate> evaluates = product.getProduct().getListEvaluate();
        model.addAttribute("hasEvaluate", false);
        if (userDetailDto != null) {
            for (Evaluate evaluate : evaluates) {
                if (evaluate.getCustomer().getId() == userDetailDto.getId()) {
                    model.addAttribute("hasEvaluate", true);
                    break;
                } else {
                    model.addAttribute("hasEvaluate", false);
                }
            }
            List<Bill> bills = findAllByUser(userDetailDto.getId());
            model.addAttribute("hasBuy", false);
            if (!bills.isEmpty()) {
                for (Bill bill : bills) {
                    for (BillProduct billProduct : bill.getBillProducts()) {
                        if (billProduct.getProduct().getId() == id && billProduct.getBill().getBillStatus().getCode().equals("CO")) {
                            model.addAttribute("hasBuy", true);
                        }
                    }
                }
            }
        }
        model.addAttribute("evaluate", evaluates.stream().filter(evaluate -> evaluate.isActive() == true).toList());
        return "user/index";
    }

    @PostMapping("/product/detail/{id}")
    public String add(@PathVariable Integer id, @RequestParam("qty") Integer qty, HttpSession session, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String url = request.getRequestURI();
        ProductDetail productDetail = productDetailService.findById(id);
        List<CartProduct> list = new ArrayList<>();
        if (session.getAttribute("list") != null) {
            list = (List<CartProduct>) session.getAttribute("list");
            if (list.isEmpty() == false) {
                boolean check = false;
                for (CartProduct cartProduct : list) {
                    if (cartProduct.getProduct().getId() == id) {
                        if (cartProduct.getQuantity() + qty < 10) {
                            if (cartProduct.getQuantity() + qty > productDetail.getQuantity()) {
                                redirectAttributes.addFlashAttribute("message", false);
                                return "redirect:" + url;
                            } else {
                                session.setAttribute("list", cartService.add(id, qty));
                                check = true;
                                break;
                            }
                        } else {
                            redirectAttributes.addFlashAttribute("message", false);
                            return "redirect:" + url;
                        }

                    }
                }
                if (check == false) {
                    session.setAttribute("list", cartService.add(id, qty));
                }
            } else {
                session.setAttribute("list", cartService.add(id, qty));
            }
        } else {
            session.setAttribute("list", cartService.add(id, qty));
        }
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
        model.addAttribute("hasEvaluate", false);
        if (userDetailDto != null) {
            for (Evaluate evaluate : evaluates.getContent()) {
                if (evaluate.getCustomer().getId() == userDetailDto.getId()) {
                    model.addAttribute("hasEvaluate", true);
                } else {
                    model.addAttribute("hasEvaluate", false);
                }
            }
            List<Bill> bills = findAllByUser(userDetailDto.getId());
            model.addAttribute("hasBuy", false);
            if (!bills.isEmpty()) {
                for (Bill bill : bills) {
                    for (BillProduct billProduct : bill.getBillProducts()) {
                        if (billProduct.getProduct().getId() == id) {
                            model.addAttribute("hasBuy", true);
                        }
                    }
                }
            }
        }

        httpSession.setAttribute("pageView", "/user/page/product/evaluate.html");
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("evaluate", evaluates.stream().filter(evaluate -> evaluate.isActive() == true).toList());
        return "user/index";
    }

    public List<Bill> findAllByUser(Integer id) {
        return billService.findAllBillByUser(id);
    }
}
