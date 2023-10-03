package com.poly.service.Impl;

import com.poly.repository.StaffRepository;
import com.poly.entity.Staff;
import com.poly.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Integer id) {
        staffRepository.deleteById(id);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findById(Integer id) {
        return staffRepository.findById(id);
    }
}
