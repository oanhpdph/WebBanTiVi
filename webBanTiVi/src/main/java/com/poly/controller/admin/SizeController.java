package com.poly.controller.admin;


import com.poly.entity.Size;

import com.poly.service.Impl.SizeServiceImpl;
import com.poly.service.SizeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {
    @Autowired
    SizeServiceImpl sizeService;



    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Size size, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sizeService.add(size), HttpStatus.OK);
    }


    @GetMapping
    public List<Size> getAll(){

        return sizeService.getAll();
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){

        sizeService.delete(id);
    }

    @PutMapping("/update")
    public Size update(@RequestBody Size size){

        return sizeService.add(size);
    }

}
