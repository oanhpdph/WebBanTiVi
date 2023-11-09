package com.poly.controller.admin;

import com.poly.service.Impl.TypeProductImpl;
import com.poly.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type")
public class TypeProductController {

    @Autowired
    private TypeProductService typeProduct;

    @GetMapping("/all")
    public ResponseEntity<?> getDataType(@RequestParam("group") Integer id) {

        return ResponseEntity.ok(typeProduct.findByGroupProduct(id));
    }
}
