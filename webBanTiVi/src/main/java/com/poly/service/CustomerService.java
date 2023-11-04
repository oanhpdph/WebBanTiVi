package com.poly.service;

import com.poly.dto.SearchStaffDto;
import com.poly.entity.Customer;
import com.poly.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    void deleteById(Integer id);

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    Page<Customer> loadData(SearchStaffDto searchStaffDto, Pageable pageable);

    Customer getCustomerByName(String username);
}
