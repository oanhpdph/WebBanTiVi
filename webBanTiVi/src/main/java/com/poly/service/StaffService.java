package com.poly.service;

import com.poly.entity.Staff;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    Staff save(Staff staff);

    void delete(Integer id);

    List<Staff> findAll();

    Optional<Staff> findById(Integer id);
}
