package com.poly.controller.admin;


import com.poly.entity.Manufacture;

import com.poly.service.Impl.ManufactureServiceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/manufacture")
public class ManufactureController {
    @Autowired
    ManufactureServiceImpl manufactureService;



    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Manufacture manufacture, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(manufactureService.add(manufacture), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){

        manufactureService.delete(id);
    }

    @PutMapping("/update")
    public Manufacture update(@RequestBody Manufacture manufacture){

        return manufactureService.add(manufacture);
    }
}
