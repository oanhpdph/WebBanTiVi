package com.poly.controller.admin;

import com.poly.entity.Bill;
import com.poly.entity.Voucher;
import com.poly.service.VoucherService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Getter
    @Setter
    public class Search {
        private String date;
        private String key;
    }


    // voucher
    @GetMapping("/voucher/list")
    public String voucher(HttpSession session, Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageRequest,
                          @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizeRequest){
     Page<Voucher> list = voucherService.getPagination(pageRequest - 1, sizeRequest);
        model.addAttribute("search", new Search());
        model.addAttribute("totalElements", list.getTotalElements());
        session.setAttribute("list", list);
        session.setAttribute("size", sizeRequest);
        session.setAttribute("page", pageRequest);
        session.setAttribute("pageView", "/admin/page/voucher/voucher.html");
        session.setAttribute("active", "/voucher/list");
        if (list.getTotalPages() < pageRequest) {
            return "redirect:/admin/voucher/list?page=" + list.getTotalPages() + "&size=" + sizeRequest;
        }
        return "/admin/layout";

    }

    @PostMapping("/voucher/list")
    public String search(@ModelAttribute(name = "search") VoucherController.Search search, Model model, HttpSession session) {
        if ("".equals(search.key.trim()) && "".equals(search.date.trim())) {
            return "redirect:/admin/voucher/list";
        }
        Page<Voucher> list = voucherService.search(search.key, search.date, 1, 10);
        session.setAttribute("list", list);
        model.addAttribute("totalElements", list.getTotalElements());
        return "/admin/layout";
    }



    @PostMapping("/voucher/add")
    public String addVoucher( @Valid @ModelAttribute("voucher")  Voucher voucher, BindingResult result,Model model) {
        this.voucherService.save(voucher);
//        model.addAttribute("listVoucher", voucherService.findAll(pageable));
        model.addAttribute("voucher", new Voucher());
        return "redirect:/admin/voucher/list";
    }

    @GetMapping("/voucher/edit/{id}")
    public String showVoucher(HttpSession session,@PathVariable("id") Integer id, Model model) {
        Voucher voucher = this.voucherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("voucher", voucher);
//        model.addAttribute("listVoucher", this.voucherService.findAll(pageable));
        return "admin/layout";
    }

    @PostMapping("/voucher/update/{id}")
    public String updatevoucher(@PathVariable("id") Integer id, @Valid Voucher voucher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "admin/layout";
        }
        this.voucherService.save(voucher);
        return "redirect:/admin/voucher/list";
    }

    @GetMapping("/voucher/delete/{id}")
    public String deletevoucher(@PathVariable("id") Integer id, Model model) {
        Voucher voucher = this.voucherService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        this.voucherService.delete(id);
        return "redirect:/admin/voucher/list";
    }
}
