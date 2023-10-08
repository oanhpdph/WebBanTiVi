package com.poly.controller.admin;

import com.poly.service.Impl.BillImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BillController {

    @Autowired
    private BillImpl billImpl;

    @GetMapping("/admin/bill_detail/{billCode}")
    public String loadBillById(HttpSession session, @PathVariable(name = "billCode") Integer idBill){
        session.setAttribute("billDetail",billImpl.getOneById(idBill));
        session.setAttribute("pageView","/admin/page/bill/bill_detail.html");
        return "/admin/layout";
    }

    @DeleteMapping("/admin/bill/delete/{billCode}")
    public String deleteBill(HttpSession session, @PathVariable(name = "billCode") Integer idBill){
        Boolean check = billImpl.delete(idBill);
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }


}
