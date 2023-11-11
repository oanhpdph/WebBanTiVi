package com.poly.controller.user;

import com.poly.dto.UserDetailDto;
import com.poly.dto.CouponRes;
import com.poly.dto.VoucherCustomerRes;
import com.poly.entity.Product;
import com.poly.entity.Voucher;
import com.poly.entity.VoucherCustomer;
import com.poly.service.Impl.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    VoucherServiceImpl voucherService;
    @Autowired
    VoucherCustomerServiceImpl voucherCustomerService;
    @Autowired
    CouponServiceImpl couponService;
    @Autowired
    CouponProductServiceImpl couponProductService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping()
    public String index(HttpSession session, Model model, @RequestParam(defaultValue = "0") int p) {

        model.addAttribute("listcoupon", couponService.getAllCouponRes());
        model.addAttribute("listvoucher", voucherService.findAllList());
        session.setAttribute("pageView", "/user/page/promotion/promotions.html");
        return "/user/index";
    }

    @GetMapping("/coupondetail/{id}")
    public String coupondetail(HttpSession session, Model model, @PathVariable("id") Integer id) {
        CouponRes couponRes = new CouponRes() {
            @Override
            public Integer getId() {
                return null;
            }

            @Override
            public String getValue() {
                return null;
            }

            @Override
            public String getImage() {
                return null;
            }

            @Override
            public Boolean getActive() {
                return null;
            }

            @Override
            public Date getDate_Start() {
                return null;
            }

            @Override
            public Date getDate_End() {
                return null;
            }

            @Override
            public Product getProduct() {
                return null;
            }
        };
        for (CouponRes x : couponService.getAllCouponRes()) {
            System.out.println(x.getId());
            if (x.getId() == id) {
                couponRes = x;
                System.out.println(x.getImage());
            }
        }
        System.out.println(couponProductService.findAllByCouponId(id).get(0).getPrice_export().intValue());
        model.addAttribute("couponres", couponRes);
        model.addAttribute("giagiam", Integer.parseInt(couponRes.getValue()));
        List<Product> listProduct = new ArrayList<>();
        model.addAttribute("listproduct", couponProductService.findAllByCouponId(id));
        session.setAttribute("pageView", "/user/page/promotion/coupondetail.html");
        return "/user/index";
    }

    @GetMapping("/voucherdetail/{id}")
    public String voucherdetail(HttpSession session, Model model, @PathVariable("id") Integer id) {
        Voucher voucher = new Voucher();
        for (Voucher x : voucherService.findAllList()) {
            if (x.getId() == id) {
                voucher = x;
            }
        }
        System.out.println(voucher.isReducedForm());
        model.addAttribute("voucher", voucher);
        session.setAttribute("pageView", "/user/page/promotion/voucherdetail.html");
        return "/user/index";
    }

    @Autowired
    UserServiceImpl customerService;

    @GetMapping("/vouchercode/{id}")
    public String vouchercode(HttpSession session, Model model, @PathVariable("id") Integer id) {
        Voucher voucher = new Voucher();
        for (Voucher x : voucherService.findAllList()) {
            if (x.getId() == id) {
                voucher = x;
            }
        }
        System.out.println(voucher.isReducedForm());
        model.addAttribute("voucher", voucher);
        LocalDate today = LocalDate.now();
        int soluong = voucherCustomerService.findAllByVoucher(id).size();
        boolean check = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailDto customerUserDetail = (UserDetailDto) userDetails;
            for (VoucherCustomer x : voucherCustomerService.findAll()) {
                if (x.getCustomer().getId() == customerUserDetail.getId()) {

                    model.addAttribute("check", check);
                    model.addAttribute("thongbao", "Mỗi khách hàng chỉ được nhận mã voucher 1 lần, hẹn quý khách ở các chương trình khuyến mại sau!");
                    session.setAttribute("pageView", "/user/page/promotion/vouchertakecode.html");
                    return "/user/index";
                }
            }
            if (soluong <= voucher.getQuantity()) {
                check = true;
                model.addAttribute("check", check);
                //add vouchercustomer;
                VoucherCustomerRes voucherCustomer = new VoucherCustomerRes();
                voucherCustomer.setCustomer(customerUserDetail.getId());
                voucherCustomer.setVoucher(id);
                voucherCustomer.setDateStart(voucher.getStartDay());
                voucherCustomer.setDateEnd(voucher.getExpirationDate());
                voucherCustomer.setActive(voucher.getActive());
                voucherCustomerService.save(voucherCustomer);
            } else {
                System.out.println("5");
                check = false;
                model.addAttribute("check", check);
                model.addAttribute("thongbao", "Số lượng voucher đã hết, hẹn quý khách ở các chương trình khuyến mại sau!");
            }
        } else {
            System.out.println("6");
            model.addAttribute("check", check);
            model.addAttribute("thongbao", "Bạn cần đăng nhập để nhận voucher!");
        }
        session.setAttribute("pageView", "/user/page/promotion/vouchertakecode.html");
        return "/user/index";
    }
}
