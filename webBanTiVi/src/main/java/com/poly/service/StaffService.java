//package com.poly.service;
//
//import com.poly.dto.SearchStaffDto;
//import com.poly.dto.SearchVoucherDto;
//import com.poly.entity.Staff;
//import com.poly.entity.Voucher;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//public interface StaffService {
//
//    Staff save(Staff staff);
//
//    void delete(Integer id);
//
//    List<Staff> findAll();
//
//    Optional<Staff> findById(Integer id);
//
//    Page<Staff> loadData(SearchStaffDto searchStaffDto, Pageable pageable);
//
//}
