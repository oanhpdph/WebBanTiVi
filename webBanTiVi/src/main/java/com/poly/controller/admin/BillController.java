package com.poly.controller.admin;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.Impl.BPServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.poly.service.Impl.BillImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BillController {

    @Autowired
    BPServiceImpl billProductService;
   @Autowired
    private BillImpl billImpl;

    @GetMapping("/admin/bill_product")
    public String index(Model model) {
        model.addAttribute("bill_product", billProductService.getAll());
        return "";
    }

    @PostMapping("/admin/bil_product/add")
    public String add(BillProduct product) {
        billProductService.save(product);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/admin/bil_product/delete/{id}")
    public String delete(BillProductId id) {
        billProductService.delete(id);
        return "redirect:/admin/bill_product";
    }

    @GetMapping("/admin/bil_product/edit/{id}")
    public String edit(@PathVariable BillProductId id, Model model) {
        BillProduct product = billProductService.edit(id);
        model.addAttribute("bill_product", product);
        return "redirect:/admin/bill_product";
    }

    @PostMapping("/admin/bil_product/update/{id}")
    public String updateUser(@PathVariable("id") BillProductId id, @ModelAttribute("billproduct") BillProduct billproduct) {
        billProductService.save(billproduct);
        return "redirect:/admin/bill_product";
    }


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
