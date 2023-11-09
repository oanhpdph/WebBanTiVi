package com.poly.controller.admin;

import com.poly.entity.Field;
import com.poly.service.FieldService;
import com.poly.service.Impl.FieldImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/field")
public class FieldController {
    @Autowired
    private FieldService field;

    @GetMapping("/all")
    public ResponseEntity<?> getDataField() {

        return ResponseEntity.ok(field.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAttributes(@RequestBody Field f) {

        return ResponseEntity.ok(field.save(f));
    }
}
