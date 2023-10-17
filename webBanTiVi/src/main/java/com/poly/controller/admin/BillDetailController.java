package com.poly.controller.admin;

import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.BillStatus;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.BillService;
import com.poly.service.BillStatusService;
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

    @GetMapping("/bill/bill_detail/{billCode}")
    public String loadBillById(HttpSession session, @PathVariable(name = "billCode") Integer idBill, Model model) {
        model.addAttribute("billDetail", billService.getOneById(idBill));
        model.addAttribute("listBillStatus", billStatusService.getAll());
        session.setAttribute("pageView", "/admin/page/bill/bill_detail.html");
        session.setAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }

    @PostMapping("/bill/bill_detail/update_status/{id}")
    public String updateUser(@PathVariable("id") Bill id,
                             @RequestParam(name = "status", required = false) String status,
                             @RequestParam(name = "payment", required = false) Integer payment) {
//        billProductService.save(billproduct);
        System.out.println(status+payment);
        return "redirect:/admin/bill/list_bill";
    }
}
