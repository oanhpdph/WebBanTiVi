package com.poly.controller.admin;

import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.BillStatus;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.BillService;
import com.poly.service.BillStatusService;
import com.poly.service.DeliveryNotesSevice;
import com.poly.service.Impl.DeliveryNotesImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class BillDetailController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillStatusService billStatusService;

    @Autowired
    private DeliveryNotesSevice deliveryNotesSevice;

    @GetMapping("/bill/bill_detail/{billCode}")
    public String loadBillById(HttpSession session, Model model,
                               @PathVariable(name = "billCode") Integer idBill) {
        model.addAttribute("deliveryNote", deliveryNotesSevice.getByIdBill(idBill));
        System.out.println(deliveryNotesSevice.getByIdBill(idBill));
        model.addAttribute("billDetail", billService.getOneById(idBill));
        session.setAttribute("pageView", "/admin/page/bill/bill_detail.html");
        session.setAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }

    @PostMapping("/bill/bill_detail/update_status/{id}")
    public String updateBillStatus(@PathVariable("id") Integer id, Model model,
                                   @RequestParam(name = "status", required = false) String status,
                                   @RequestParam(name = "paymentStatus", required = false) Integer paymentStatus) {
        Bill bill = new Bill();
        bill.setBillStatus(billStatusService.getOneBycode(status));
        bill.setPaymentStatus(paymentStatus);
        billService.update(bill, id);
        return "redirect:/admin/bill/bill_detail/" + id;
    }

}
