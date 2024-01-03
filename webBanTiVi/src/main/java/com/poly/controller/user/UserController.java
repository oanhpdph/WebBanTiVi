package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.ChangeInforDto;
import com.poly.dto.HistoryBillReturnDto;
import com.poly.dto.ReturnDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.Bill;
import com.poly.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    HistoryBillProductService historyBillProductService;


    @Autowired
    CheckLogin checkLogin;


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
        List<HistoryBillReturnDto> listHistoryDto = this.historyBillProductService.findAllHistoryBillReturnByIdBill(id);
        BigDecimal total = BigDecimal.ZERO;
        for(HistoryBillReturnDto hBillReturnDto : listHistoryDto){
              total =total.add(hBillReturnDto.getReturnMoney());
              if(bill.getTotalPrice().subtract(total).compareTo(bill.getVoucherValue())<0){
                  hBillReturnDto.setReturnMoney(hBillReturnDto.getReturnMoney().subtract(bill.getVoucherValue()));
              }
        }
        model.addAttribute("bill", bill);
        model.addAttribute("listHistoryReturn", listHistoryDto);
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
        List<Bill> billListNew = new ArrayList<>();
        for(Bill bill : billList){
            Bill newBill = this.historyBillProductService.listBillWhenReturned(String.valueOf(bill.getId()));
            billListNew.add(newBill);
        }
        List<Boolean> checkQuantity = this.billService.checkConditionReturn();
        List<Boolean> listeck = this.billService.checkValidationReturn();
        model.addAttribute("check", listeck);
        model.addAttribute("bill", billListNew);
        model.addAttribute("checkQuantity", checkQuantity);
        return "/user/index";
    }

    @PostMapping("/return/{id}")
    public String returnProduct(@PathVariable("id") Integer id,
                                @RequestBody List<ReturnDto> returnDto, Model model, RedirectAttributes redirectAttributes) {
        this.billService.logicBillReturn(id, returnDto);
        Bill bill = this.billService.getOneById(id);
        String code = bill.getCode();
        redirectAttributes.addFlashAttribute("return", "return");
        if (code == null || code != "") {
            return "redirect:/search_order_user?search=" + code;
        }
        return "redirect:/order";
    }


    @GetMapping("/search_order")
    public String getSearch(HttpSession session) {
        session.setAttribute("pageView", "/user/page/search/search_order.html");
        return "/user/index";
    }

    @GetMapping("/search_order_user")
    public String getSearchOder(@ModelAttribute("search") String search, HttpSession session, Model model) {
        Bill bill = this.billService.findBillNewReturnByCode(search.trim());
        if (bill == null) {
            model.addAttribute("errorSearch", "Xin lỗi! Đơn hàng bạn tìm không tồn tại trên hệ thống!");
            return "/user/index";
        }
        Boolean check = this.billService.checkValidateReturnNologin(search.trim());
        Boolean checkQuantity = this.billService.checkConditionReturnNoLogin(search.trim());
        session.setAttribute("checkQuantity", checkQuantity);
        session.setAttribute("checkReturn", check);
        session.setAttribute("bill", bill);
        return "redirect:/search_order";
    }

    @GetMapping("/order/remove/{id}")
    public String removeOrder(@PathVariable("id") Integer id) {
        Bill billCancel = this.billService.getOneById(id);
        billCancel.setBillStatus(this.billStatusService.getOneBycode("CA"));
        this.billService.add(billCancel);
        if (checkLogin.checkLogin() != null) {
            UserDetailDto userDetailDto = checkLogin.checkLogin();
            List<Bill> billList = this.billService.findAllBillByUser(userDetailDto.getId());
            for (Bill bill : billList) {
                if (billCancel.equals(bill)) {
                    return "redirect:/order";
                }
            }
        }
        return "redirect:/search_order_user?search=" + billCancel.getCode();
    }

}
