package com.poly.controller.user;

import com.poly.common.UploadFile;
import com.poly.dto.ChangeInforDto;
import com.poly.dto.ImageReturnDto;
import com.poly.dto.ReturnDto;
import com.poly.dto.UserDetailDto;
import com.poly.entity.Bill;
import com.poly.entity.BillProduct;
import com.poly.entity.BillStatus;
import com.poly.entity.ImageReturned;
import com.poly.service.BillProductService;
import com.poly.service.BillService;
import com.poly.service.BillStatusService;
import com.poly.service.ImageReturnService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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



    @GetMapping("/tivi")
    public String loadProduct(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/product/tivi.html");
        model.addAttribute("active", "tivi");
        return "/user/index";
    }

    @GetMapping("/accessory")
    public String loadAccessory(HttpSession session,Model model) {
        session.setAttribute("pageView", "/user/page/product/accessory.html");
        model.addAttribute("active", "accesory");
        return "/user/index";
    }

    @GetMapping("/invoice")
    public String loadInvoice(HttpSession session) {
        session.setAttribute("pageView", "/user/page/invoice/search_invoice.html");
        return "/user/index";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String profile(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/profile/profile.html");
        model.addAttribute("changeInfo", new ChangeInforDto());
        return "/user/index";
    }

    @GetMapping("/invoice/invoice_detail/{id}")
    public String loadInvoiceDetail(HttpSession session, Model model, @PathVariable("id") Integer id) {
        Bill bill = this.billService.getOneById(id);
        BigDecimal total = BigDecimal.valueOf(0);
        for (BillProduct billProduct : bill.getBillProducts()) {
            BigDecimal bigDecimal = billProduct.getPrice().multiply(BigDecimal.valueOf(billProduct.getQuantity()));
            total = total.add(bigDecimal);
        }
        model.addAttribute("total", total);
        model.addAttribute("bill", bill);
        model.addAttribute("billProducts", bill.getBillProducts());
        session.setAttribute("pageView", "/user/page/invoice/detail_invoice.html");
        return "/user/index";
    }

    @GetMapping("/order")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String order(HttpSession session, Model model) {
        session.setAttribute("pageView", "/user/page/profile/order.html");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailDto customerUserDetail = (UserDetailDto) userDetails;
            List<Bill> billList = this.billService.findAllBillByUser(customerUserDetail.getId());
            Date today = new Date();
            model.addAttribute("today", today);
            model.addAttribute("bill", billList);
        return "/user/index";
    }

    @PostMapping("/return/{id}")
    public String returnProduct(HttpSession session,
                                @PathVariable("id") Integer id,
                                @RequestBody List<ReturnDto> returnDto) {
        for (ReturnDto dto : returnDto) {
            for (ImageReturnDto image : dto.getImage()) {
                ImageReturned img = new ImageReturned();
                BillProduct billProduct = this.billProductService.edit(image.getIdBillProduct());
                billProduct.setStatus(1); // yêu cầu trả hàng
                billProduct.setReason(dto.getReason());
                billProduct.setQuantityReturn(Integer.parseInt(dto.getQuantityReturn()));
                img.setBillProduct(billProduct);
                img.setNameImage(image.getNameImage());
                this.imageReturnService.save(img);
            }
        }
        BillStatus billStatus = this.billStatusService.getOneBycode("RR");
        Bill bill = this.billService.getOneById(id);
        bill.setBillStatus(billStatus);
        this.billService.add(bill);
        return "redirect:/order";
    }

    @PostMapping(path = "/returnImage")
    public ResponseEntity<?> upload(@RequestParam(value = "images", required = false) List<MultipartFile> list) throws IOException {
        for (MultipartFile multipartFile : list) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            UploadFile.saveFile("src/main/resources/static/image", fileName, multipartFile);
        }
        return ResponseEntity.ok(200);
    }

    @GetMapping("/search_order")
    public String getSearch(HttpSession session){
        session.setAttribute("pageView", "/user/page/search/search_order.html");
        return "/user/index";
    }

    @PostMapping("/search_order_user")
    public String getSearchOder(@ModelAttribute("search") String search,HttpSession session){
         Bill bill =  this.billService.findByCode(search);
         session.setAttribute("bill",bill);
        return "redirect:/search_order";
    }

}
