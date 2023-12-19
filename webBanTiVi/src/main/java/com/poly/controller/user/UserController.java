package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.ChangeInforDto;
import com.poly.dto.ReturnDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.Bill;
import com.poly.service.BillProductService;
import com.poly.service.BillService;
import com.poly.service.BillStatusService;
import com.poly.service.ImageReturnService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    BillService billService;

    @Autowired
    BillStatusService billStatusService;

    @Autowired
    ImageReturnService imageReturnService;

    @Autowired
    BillProductService billProductService;


    @Autowired
    CheckLogin checkLogin;


    Date today = new Date();

    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session) {
        session.setAttribute("pageView", "/user/page/invoice/search_invoice.html");
        return "/user/index";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority('USER','STAFF','ADMIN')")
    public String profile(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/profile/profile.html");
        model.addAttribute("changeInfo", new ChangeInforDto());
        return "/user/index";
    }

    @GetMapping("/invoice/invoice_detail/{id}")
    public String loadInvoiceDetail(HttpSession session, Model model, @PathVariable("id") Integer id) {
        Bill bill = this.billService.getOneById(id);
        model.addAttribute("bill", bill);
        model.addAttribute("billProducts", bill.getBillProducts());
        session.setAttribute("pageView", "/user/page/invoice/detail_invoice.html");
        return "/user/index";
    }

    @GetMapping("/order")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN','STAFF')")
    public String order(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/profile/order.html");
        UserDetailDto customerUserDetail = this.checkLogin.checkLogin();
        List<Bill> billList = this.billService.findAllBillByUser(customerUserDetail.getId());
        List<Bill> listBillFilter = this.billService.listBillFilter(billList);
        List<Bill> listBillFilterStill = this.billService.listBillFilterStill(billList);
        model.addAttribute("listBillCheck", listBillFilter);
        model.addAttribute("listBillFilterStill", listBillFilterStill);
        model.addAttribute("today", today);
        model.addAttribute("bill", billList);
        return "/user/index";
    }

    @PostMapping("/return/{id}")
    public String returnProduct(@PathVariable("id") Integer id,
                                @RequestBody List<ReturnDto> returnDto,Model model, RedirectAttributes redirectAttributes) {
        this.billService.logicBillReturn(id, returnDto);
        Bill bill = this.billService.getOneById(id);
        String code = bill.getCode();
        redirectAttributes.addFlashAttribute("return","return");
        if(checkLogin.checkLogin() != null) {
            UserDetailDto customerUserDetail = checkLogin.checkLogin();
            List<Bill> billList = this.billService.findAllBillByUser(customerUserDetail.getId());
            for(Bill billCheck : billList){
                if(bill.equals(billCheck)) {
                    model.addAttribute("errorSearch", "Xin lỗi! Đơn hàng này không tồn tại trong lịch sử đơn hàng của bạn!");
                    return "/user/index";
                }
            }
            return "redirect:/order";
        }
        if (checkLogin.checkLogin() == null) {
            return "redirect:/search_order_user?search="+code;
        }
        return null;
    }


    @GetMapping("/search_order")
    public String getSearch(HttpSession session) {
        session.setAttribute("pageView", "/user/page/search/search_order.html");
        return "/user/index";
    }

    @GetMapping("/search_order_user")
    public String getSearchOder(@ModelAttribute("search") String search, HttpSession session, Model model) {
        Optional<Bill> bill = this.billService.findByCode(search.trim());
        if (bill.isEmpty()) {
            model.addAttribute("errorSearch", "Xin lỗi! Đơn hàng bạn tìm không tồn tại trên hệ thống!");
            return "/user/index";
        }
        Date today = new Date();
        session.setAttribute("today", today);
        session.setAttribute("bill", bill.get());
        session.setAttribute("bool", this.billService.checkBillNoLogin(search));
        return "redirect:/search_order";
    }

    @GetMapping("/order/remove/{id}")
    public String removeOrder(@PathVariable("id") Integer id) {
        Bill billCancel = this.billService.getOneById(id);
        billCancel.setBillStatus(this.billStatusService.getOneBycode("CC"));
        this.billService.add(billCancel);
        if(checkLogin.checkLogin() !=null){
            return "redirect:/order";
        }
        return "redirect:/search_order_user?search="+billCancel.getCode();
    }

}
