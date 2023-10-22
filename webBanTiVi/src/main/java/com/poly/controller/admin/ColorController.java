package com.poly.controller.admin;

import com.poly.entity.Color;

import com.poly.service.Impl.ColorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    ColorServiceImpl colorService;



    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Color color, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(colorService.add(color), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){

        colorService.delete(id);
    }

    @PutMapping("/update")
    public Color update(@RequestBody Color color){

        return colorService.add(color);
    }




}
