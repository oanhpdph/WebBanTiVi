package com.poly.controller.user;

import com.poly.entity.Voucher;
import com.poly.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class PayController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/voucher/getone")
    public ResponseEntity<?> getOne(@RequestParam("id") Integer id) {
        Optional<Voucher> voucher = this.voucherService.findById(id);
        if (voucher.isPresent()) {
            return ResponseEntity.ok(voucher);
        }
        return ResponseEntity.ok(null);
    }
}
