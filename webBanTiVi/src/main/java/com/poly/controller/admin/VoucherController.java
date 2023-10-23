package com.poly.controller.admin;

import com.poly.common.UploadFile;
import com.poly.dto.SearchVoucherDto;
import com.poly.entity.Voucher;
import com.poly.service.VoucherService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;



    // voucher
    @GetMapping("/voucher/list")
    public String voucher(HttpSession session, Model model,
                          @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageRequest,
                          @RequestParam(name = "size", required = false, defaultValue = "2") Integer sizeRequest,
                          @ModelAttribute(name = "search") SearchVoucherDto search
                          ) {
        if(pageRequest<=0){
            pageRequest=1;
        }
        if(sizeRequest<=0){
            sizeRequest=1;
        }

        session.setAttribute("size", sizeRequest);
        session.setAttribute("page", pageRequest);

        Pageable pageable = PageRequest.of(pageRequest - 1, sizeRequest);
        Page<Voucher> vouchers = voucherService.loadData(search, pageable);

        model.addAttribute("totalElements", vouchers.getTotalElements());
        session.setAttribute("list", vouchers);

        session.setAttribute("pageView", "/admin/page/voucher/voucher.html");
        session.setAttribute("active", "/voucher/list");
        if (vouchers.getTotalPages() < pageRequest) {
            return "redirect:/admin/voucher/list?page=" + vouchers.getTotalPages() + "&size=" + sizeRequest;
        }
        return "/admin/layout";

    }

//    @PostMapping("/voucher/list")
//    public String search(@ModelAttribute(name = "search") VoucherController.Search search, Model model, HttpSession session) {
//        if ("".equals(search.key.trim()) && "".equals(search.date.trim())) {
//            return "redirect:/admin/voucher/list";
//        }
//        Page<Voucher> list = voucherService.search(search.key, search.date, 1, 1);
//        session.setAttribute("list", list);
//        model.addAttribute("totalElements", list.getTotalElements());
//        return "/admin/layout";
//    }


    @PostMapping("/voucher/add")
    public String addVoucher(
            HttpSession session,
            @Valid @ModelAttribute("voucher") Voucher voucher,
            BindingResult result, Model model,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageRequest,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer sizeRequest,
            @RequestParam("image") MultipartFile file,
            @ModelAttribute(name = "search") SearchVoucherDto search
            ) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        voucher.setImage(fileName);
        this.voucherService.save(voucher);
        String uploadDir = "src/main/resources/static/image"; // đường dẫn upload
        try {
            UploadFile.saveFile(uploadDir, fileName, file);
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
        Pageable pageable = PageRequest.of(pageRequest - 1, sizeRequest);
        Page<Voucher> vouchers = voucherService.loadData(search, pageable);
        model.addAttribute("totalElements", vouchers.getTotalElements());
        session.setAttribute("list", vouchers);
        session.setAttribute("size", sizeRequest);
        session.setAttribute("page", pageRequest);
        if (vouchers.getTotalPages() < pageRequest) {
            return "redirect:/admin/voucher/list?page=" + vouchers.getTotalPages() + "&size=" + sizeRequest;
        }
        return "redirect:/admin/voucher/list";
    }

    @GetMapping("/voucher/edit/{id}")
    public String showVoucher(HttpSession session, @PathVariable("id") Integer id, Model model) {
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
