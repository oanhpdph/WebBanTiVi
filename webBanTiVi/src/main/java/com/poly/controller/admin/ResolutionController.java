package com.poly.controller.admin;

import com.poly.entity.Resolution;
import com.poly.service.Impl.ResolutionServiceImpl;
import com.poly.service.ResolutionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resolution")
public class ResolutionController {
    @Autowired
    ResolutionServiceImpl resolutionService;

    @GetMapping
    public List<Resolution>getAll(){
        return resolutionService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?>add(@Valid Resolution resolution, BindingResult result){
        resolutionService.add(resolution);
        return ResponseEntity.ok("them thanh cong");
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        resolutionService.delete(id);
    }

    @PutMapping("/update")
    public Resolution update(@RequestBody Resolution resolution){
        return resolutionService.add(resolution);
    }
}
