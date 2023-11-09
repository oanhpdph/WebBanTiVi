package com.poly.controller.admin;

import com.poly.common.UploadFile;
import com.poly.dto.ProductDetailDto;
import com.poly.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @PostMapping("/save-product")
    public ResponseEntity<?> saveProductDetail(@RequestBody List<ProductDetailDto> productDetailDto) {
        return ResponseEntity.ok(productDetailService.saveList(productDetailDto));
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<?> upload(@RequestParam(value = "images", required = false) List<MultipartFile> list) throws IOException {
        for (MultipartFile multipartFile : list) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            UploadFile.saveFile("src/main/resources/static/image/product", fileName, multipartFile);
        }
        return ResponseEntity.ok(200);
    }

    @GetMapping(path = "/same-product")
    public ResponseEntity<?> getSameProduct(@RequestParam("same-code") String sameCode) {
        return ResponseEntity.ok(productDetailService.getSameProduct(sameCode));
    }

//    @GetMapping(path = "/get-all")
//    public ResponseEntity<?> getAll() {
//        return ResponseEntity.ok(productDetailService.findAll());
//    }

}
