package com.poly.controller.user;

import com.poly.entity.Product;
import com.poly.service.Impl.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductUserController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    BrandServiceImpl brandService;
    @Autowired
    ColorServiceImpl colorService;
    @Autowired
    ResolutionServiceImpl resolutionService;
    @Autowired
    SizeServiceImpl sizeService;
    @Autowired
    TypeServiceImpl typeService;
    @Data
    @AllArgsConstructor
    public static class PriceRange{
        int id;
        int minValue;
        int maxValue;
        String display;
    }
    List<PriceRange> priceRangeList = Arrays.asList(
            new PriceRange(0,0, Integer.MAX_VALUE, "Tất cả"),
            new PriceRange(1,0, 10000000, "Dưới 10 triệu"),
            new PriceRange(2,10000000, 20000000, "Từ 10-20 triệu"),
            new PriceRange(3,20000000, Integer.MAX_VALUE, "Trên 20 triệu")
    );
    @GetMapping("/tivi")
    public String index(HttpSession session, Model model, @RequestParam(defaultValue = "0") int p,
                        @RequestParam(defaultValue="") String keyword,
                        @RequestParam(defaultValue="") Integer idBrand,
                        @RequestParam(defaultValue="") Integer idColor,
                        @RequestParam(defaultValue="") Integer idOrigin,
                        @RequestParam(defaultValue="") Integer idReso,
                        @RequestParam(defaultValue="") Integer idSize,
                        @RequestParam(defaultValue="") Integer idType,
                        @RequestParam(defaultValue="0") int priceRangeId) {
        int minPrice = priceRangeList.get(priceRangeId).minValue;
        int maxPrice = priceRangeList.get(priceRangeId).maxValue;
        model.addAttribute("brandList", brandService.getAll());
        model.addAttribute("colorList", colorService.findAll());
        model.addAttribute("resoList", resolutionService.getAll());
        model.addAttribute("sizeList", sizeService.getAll());
        model.addAttribute("typeList", typeService.findAll());
        model.addAttribute("list",productService.findAll());
        // TODO: Search & paginate
        if(!keyword.isEmpty()) {
            List<Product> list = productService.findAllByAll(idBrand, idColor, idOrigin, idReso, idSize, idType, minPrice, maxPrice);
            model.addAttribute("list", list);
        }
        else {
            List<Product> list = productService.findAllByKeywordAndPrice(keyword,minPrice,maxPrice);
            model.addAttribute("list", list);
        }
        session.setAttribute("pageView", "/user/page/product/tivi.html");
        return "/user/index";
    }
}
