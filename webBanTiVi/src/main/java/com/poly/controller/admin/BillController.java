package com.poly.controller.admin;

import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import com.poly.service.BillService;
import com.poly.service.Impl.BPServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class BillController {

    @Autowired
    BPServiceImpl billProductService;

    @Autowired
    private BillService billService;

    public class Search {
        private String keyword;
        private String date;
    }

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
    public String loadBillById(HttpSession session, @PathVariable(name = "billCode") Integer idBill,Model model) {
        session.setAttribute("billDetail", billService.getOneById(idBill));
        model.addAttribute("pageView", "/admin/page/bill/bill_detail.html");
        return "/admin/layout";
    }

    @DeleteMapping("/bill/delete/{billCode}")
    public String deleteBill(HttpSession session, @PathVariable(name = "billCode") Integer idBill,Model model) {
        Boolean check = billService.delete(idBill);
        model.addAttribute("pageView", "/admin/page/bill/bill.html");
        model.addAttribute("active", "/bill/list_bill");
        return "/admin/layout";
    }


    @GetMapping(value = {"/bill/list_bill"})
    public String loadBill(HttpSession session, Model model, @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageRequest, @RequestParam(name = "size", required = false, defaultValue = "1") Integer sizeRequest) {
        Page<Bill> list = billService.getPagination(pageRequest, sizeRequest);
        Integer totalPage = list.getTotalPages();
        model.addAttribute("search", new Search());
        model.addAttribute("totalElement", list.getTotalElements());
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("listBill", billService.getALlDto(pageRequest, sizeRequest));
        session.setAttribute("size", sizeRequest);
        session.setAttribute("page", pageRequest);
        model.addAttribute("pageView", "/admin/page/bill/bill.html");
        model.addAttribute("active", "/bill/list_bill");
        if (totalPage < pageRequest) {
            return "redirect:/admin/bill/list_bill?page=" + totalPage + "&size=" + sizeRequest;
        }
        return "/admin/layout";
    }

    @GetMapping("/bill/list_bill/search")
    public String search(@ModelAttribute("search") Search search, HttpSession session,Model model) {
        Integer page = (Integer) session.getAttribute("page");
        Integer size = (Integer) session.getAttribute("size");
        model.addAttribute("listBill",billService.search(search.keyword,page,size));
        return "/admin/layout";
    }
}
