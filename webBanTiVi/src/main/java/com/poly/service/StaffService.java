package com.poly.service;

import com.poly.dto.StaffDto;
import com.poly.entity.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    Staff save(Staff staff);

    void delete(Integer id);

    List<Staff> findAll();

    Optional<Staff> findById(Integer id);

    Staff findByUsernameAndPassword(StaffDto dto);
}
