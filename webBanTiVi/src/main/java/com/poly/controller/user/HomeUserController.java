package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.ProductDetailDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.*;
import com.poly.service.BrandService;
import com.poly.service.CartService;
import com.poly.service.CustomerService;
import com.poly.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeUserController {
    @Autowired
    CartService cartService;

    @Autowired
    private CheckLogin checkLogin;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @GetMapping("/")
    public String loadHome(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/home/home.html");
        model.addAttribute("active", "home");
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setSort(1);
        productDetailDto.setSize(20);
        productDetailDto.setActive(true);

        productDetailDto.setGroup(1);

        Page<Product> page = productService.findAll(productDetailDto);
        model.addAttribute("listTivi", getProduct(new ArrayList<>(), page.getContent()));

        productDetailDto.setGroup(2);
        Page<Product> phuKien = productService.findAll(productDetailDto);
        model.addAttribute("listPhuKien", getProduct(new ArrayList<>(), phuKien.getContent()));

        productDetailDto.setGroup(0);
        Page<Product> productNew = productService.findAll(productDetailDto);
        model.addAttribute("listNewProduct", getProduct(new ArrayList<>(), productNew.getContent()));

        productDetailDto.setSort(3);
        Page<Product> topRate = productService.findAll(productDetailDto);
        model.addAttribute("listTopRate", getProduct(new ArrayList<>(), topRate.getContent()));


        UserDetailDto userDetailDto = checkLogin.checkLogin();
        if (userDetailDto != null) {
            Cart cart = cartService.getOneByUser(userDetailDto.getId());
            if (cart == null) {
                cart = new Cart();
                Optional<Users> optional = customerService.findById(userDetailDto.getId());
                if (optional.isPresent()) {
                    cart.setCustomer(optional.get());
                    cartService.save(cart);
                }
            }
            session.setAttribute("list", cart.getListCartPro());
            session.setAttribute("user", userDetailDto);
        } else {
            session.setAttribute("user", null);
        }
        return "/user/index";
    }

    @GetMapping("/tivi")
    public String loadProduct(HttpSession session, Model model) {
        List<Brand> list = brandService.findAll();
        model.addAttribute("listBrand", list.stream().filter(brand -> brand.isActive() == true).toList());

        session.setAttribute("pageView", "/user/page/product/tivi.html");
        model.addAttribute("active", "tivi");
        return "/user/index";
    }

    @GetMapping("/tivi/get")
    public ResponseEntity<?> loadProductTivi(@ModelAttribute ProductDetailDto productDetailDto) {
//        productDetailDto.setSize(1);
        productDetailDto.setActive(true);
        productDetailDto.setGroup(1);

        Page<Product> page = productService.findAll(productDetailDto);
        return ResponseEntity.ok(getProduct(new ArrayList<>(), page.getContent()));
    }

    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session, Model model) {
        List<Brand> list = brandService.findAll();
        model.addAttribute("listBrand", list.stream().filter(brand -> brand.isActive() == true).toList());

        session.setAttribute("pageView", "/user/page/product/accessory.html");
        model.addAttribute("active", "accesory");
        return "/user/index";
    }

    @GetMapping("/accessory/get")
    public ResponseEntity<?> apiLoadAccessory(@ModelAttribute ProductDetailDto productDetailDto) {
        productDetailDto.setActive(true);
        productDetailDto.setGroup(2);

        Page<Product> page = productService.findAll(productDetailDto);
        return ResponseEntity.ok(getProduct(new ArrayList<>(), page.getContent()));
    }

    private List<ProductDetailDto> getProduct(List<ProductDetailDto> listReturn, List<Product> listInput) {
        for (Product product : listInput) {
            ProductDetailDto productDetailDto1 = new ProductDetailDto();
            productDetailDto1.setNameProduct(product.getNameProduct() + " " + product.getSku());
            //name
            int temp = 0;
            if (product.getActive() == true) {
                for (ProductDetail productDetail : product.getProductDetails()) {
                    if (productDetail.isActive()) {
                        for (Image image : productDetail.getListImage()) {
                            if (image.isLocation()) {
                                productDetailDto1.setImage(image.getLink());
                                // ảnh
                                break;
                            }
                        }

                        if (productDetail.getCoupon() != null && productDetail.getCoupon().isActive() && (LocalDate.now().isAfter(productDetail.getCoupon().getDateStart().toLocalDate()) && LocalDate.now().isBefore(productDetail.getCoupon().getDateEnd().toLocalDate()))) {
                            BigDecimal reduceMoney = productDetail.getPriceExport().multiply(BigDecimal.valueOf(Integer.parseInt(productDetail.getCoupon().getValue()))).divide(BigDecimal.valueOf(100));
                            productDetailDto1.setReduceMoney(productDetail.getPriceExport().subtract(reduceMoney));
                            //giá sau giảm
                            productDetailDto1.setPrice(productDetail.getPriceExport());
                            // giá trước giảm
                        } else {
                            productDetailDto1.setPrice(BigDecimal.valueOf(0));
                            productDetailDto1.setReduceMoney(productDetail.getPriceExport());
                        }

                        productDetailDto1.setId(productDetail.getId());
                        productDetailDto1.setPoint(product.getAvgPoint());
                        productDetailDto1.setQuantityEvalute(product.getListEvaluate().stream().filter(evaluate -> evaluate.isActive() == true).toList().size());
                        temp = 1;
                        break;
                    }
                }
                if (temp == 1) {
                    listReturn.add(productDetailDto1);
                }
            }
        }
        return listReturn;
    }
}
