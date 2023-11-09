package com.poly.controller.user;

import com.poly.entity.Brand;
import com.poly.entity.ImageProduct;
import com.poly.entity.Product;
import com.poly.service.ImageProductService;
import com.poly.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/product_detail")
public class ProductDetailController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ColorServiceImpl colorService;
    @Autowired
    private SizeServiceImpl sizeService;
    @Autowired
    private BrandServiceImpl brandService;
    @Autowired
    private ImageProductServiceimpl imageProductServiceimpl;

    private final Path root = Paths.get("src/main/resources/static/imgage");
    @GetMapping("/index")
    public String hienThi(Model model){
        model.addAttribute("listColor",colorService.findAll());
        model.addAttribute("listImageProduct",imageProductServiceimpl.getAll());
        model.addAttribute("listSize",sizeService.getAll());
        model.addAttribute("listBrand",brandService.getAll());
        model.addAttribute("Product",new Product());
        model.addAttribute("view","/user/page/product/tivi.html");
        return "index";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("listColor",colorService.findAll());
        model.addAttribute("listImageProduct",imageProductServiceimpl.getAll());
        model.addAttribute("listSize",sizeService.getAll());
        model.addAttribute("listBrand",brandService.getAll());
        model.addAttribute("Product",new Product());
        model.addAttribute("view","/user/page/product/detail.html");
        return"index";

    }
    @GetMapping("/indexcus/{productDetailId}" )
    public String show_data_product_cus(@PathVariable("productDetailId") Integer productDetailId, Model model){
        model.addAttribute("product",productService.getProductDetailById(productDetailId));
        model.addAttribute("image",imageProductServiceimpl.getByProductDetailId(productDetailId).get(0));
        model.addAttribute("listImage",imageProductServiceimpl.getByProductDetailId(productDetailId));
        model.addAttribute("view", "/user/page/product/tivi.html");
        return "/user/index";
    }

}
