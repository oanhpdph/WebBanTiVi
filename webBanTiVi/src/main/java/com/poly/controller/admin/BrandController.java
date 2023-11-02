package com.poly.controller.admin;

import com.poly.entity.Brand;



import com.poly.service.Impl.BrandServiceImpl;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ADMIN','STAFF')")
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandServiceImpl brandService;


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Brand brand, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(brandService.add(brand), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {

        brandService.delete(id);
    }

    @PutMapping("/update")
    public Brand update(@RequestBody Brand brand) {

        return brandService.add(brand);
    }

}
