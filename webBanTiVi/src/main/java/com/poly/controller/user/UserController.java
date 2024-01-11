package com.poly.controller.user;

import com.poly.common.CheckLogin;
import com.poly.dto.ChangeInforDto;
import com.poly.dto.HistoryBillReturnDto;
import com.poly.dto.ReturnDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
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
    VoucherService voucherService;


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
        BigDecimal totalReturn = BigDecimal.ZERO;
        int check = 0;
        if(bill.getVoucher() !=null && bill.getVoucher().getMinimumValue() != null) {
            for (HistoryBillReturnDto hBillReturnDto : listHistoryDto) {
                totalReturn = totalReturn.add(hBillReturnDto.getReturnMoney());
                if (bill.getTotalPrice().subtract(totalReturn).compareTo(bill.getVoucher().getMinimumValue()) < 0 && check == 0) {
                    hBillReturnDto.setReturnMoney(hBillReturnDto.getReturnMoney().subtract(bill.getVoucherValue()));
                    check = 1;
                }
            }
        }
        BigDecimal totalBill = BigDecimal.ZERO;
        for(BillProduct billProduct: bill.getBillProducts()){
            totalBill= totalBill.add(billProduct.getPrice().multiply(BigDecimal.valueOf(billProduct.getQuantity())));
        }
        model.addAttribute("bill", bill);
        model.addAttribute("totalBill", totalBill);
        model.addAttribute("listHistoryReturn", listHistoryDto);
        model.addAttribute("billProducts", bill.getBillProducts());
        session.setAttribute("pageView", "/user/page/invoice/detail_invoice.html");
        return "/user/index";
    }

    @GetMapping("/order")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN','STAFF')")
    public String order(HttpSession session, Model model) {
        UserDetailDto customerUserDetail = this.checkLogin.checkLogin();
        List<Bill> billList = this.billService.findAllBillByUser(customerUserDetail.getId());
        List<Boolean> listcheck = this.billService.checkValidationReturn();
        model.addAttribute("check", listcheck);
        model.addAttribute("bill", billList);
//        session.setAttribute("activeOrder", 0);
        session.setAttribute("pageView", "/user/page/profile/order.html");
        return "/user/index";
    }
//    @GetMapping("/order/status/{id}")
//    @PreAuthorize("hasAnyAuthority('USER','ADMIN','STAFF')")
//    public String orderStatus(HttpSession session, Model model, @PathVariable("id") Integer id) {
//        UserDetailDto customerUserDetail = this.checkLogin.checkLogin();
//        List<Bill> billList = this.billService.findBillByUserAndStatus(customerUserDetail.getId(), id);
//        List<Boolean> listcheck = this.billService.checkValidationReturn();
//        model.addAttribute("check", listcheck);
//        model.addAttribute("bill", billList);
//        model.addAttribute("activeOrder",'0');
//        session.setAttribute("pageView", "/user/page/profile/order.html");
//        return "/user/index";
//    }

    @GetMapping("/order_return/detail/{id}")
    public String viewReturnOrder(HttpSession session, Model model,@PathVariable("id") Integer id) {
        session.setAttribute("pageView", "/user/page/profile/return.html");
        Bill newBill = this.historyBillProductService.listBillWhenReturned(String.valueOf(id));
        Boolean checkQuantity = this.billService.checkQuantityBillReturn(id);
        model.addAttribute("bill", newBill);
        model.addAttribute("checkQuantity", checkQuantity);
        return "/user/index";
    }


    @PostMapping("/return/{id}")
    public String returnProduct(@PathVariable("id") Integer id,
                                @RequestBody List<ReturnDto> returnDto, Model model, RedirectAttributes redirectAttributes) {
        this.billService.logicBillReturn(id, returnDto);
        Bill bill = this.billService.getOneById(id);
        redirectAttributes.addFlashAttribute("return", "return");
        UserDetailDto customerUserDetail = this.checkLogin.checkLogin();
        if (customerUserDetail == null) {
            return "redirect:/search_order_user?search=" + bill.getCode();
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
        session.setAttribute("codeSearch", search);
        session.setAttribute("billSearch", bill);
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
