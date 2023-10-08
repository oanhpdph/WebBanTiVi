package com.poly.controller.admin;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.BillService;
import com.poly.service.Impl.BPServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.poly.service.Impl.BillImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class BillController {

    @Autowired
    BPServiceImpl billProductService;
    @Autowired
    private BillService billService;

    @GetMapping("/bill_product")
    public String index(Model model) {
        model.addAttribute("bill_product", billProductService.getAll());
        return "";
    }

    @PostMapping("/bil_product/add")
    public String add(BillProduct product) {
        billProductService.save(product);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/bil_product/delete/{id}")
    public String delete(BillProductId id) {
        billProductService.delete(id);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/bil_product/edit/{id}")
    public String edit(@PathVariable BillProductId id, Model model) {
        BillProduct product = billProductService.edit(id);
        model.addAttribute("bill_product", product);
        return "redirect:/admin/bill_product";
    }

    @PostMapping("/bil_product/update/{id}")
    public String updateUser(@PathVariable("id") BillProductId id, @ModelAttribute("billproduct") BillProduct billproduct) {
        billProductService.save(billproduct);
        return "redirect:/admin/bill_product";
    }


    @GetMapping("/bill_detail/{billCode}")
    public String loadBillById(HttpSession session, @PathVariable(name = "billCode") Integer idBill) {
        session.setAttribute("billDetail", billService.getOneById(idBill));
        session.setAttribute("pageView", "/admin/page/bill/bill_detail.html");
        return "/admin/layout";
    }

    @DeleteMapping("/bill/delete/{billCode}")
    public String deleteBill(HttpSession session, @PathVariable(name = "billCode") Integer idBill) {
        Boolean check = billService.delete(idBill);
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }

    @GetMapping("/bill/list_bill")
    public String loadBill(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/order/order.html");
        session.setAttribute("active", "/order");
        session.setAttribute("listBill", billService.getALl());
        session.setAttribute("pageView", "/admin/page/bill/bill.html");
        session.setAttribute("active", "/bill/list_bill");
        return "admin/layout";
    }

    @GetMapping("/bill/payment_method")
    public String loadPaymentMethod(HttpSession session) {
        session.setAttribute("pageView", "/admin/page/bill/payment_method.html");
        session.setAttribute("active", "/bill/payment_method");
        return "admin/layout";
    }

}
