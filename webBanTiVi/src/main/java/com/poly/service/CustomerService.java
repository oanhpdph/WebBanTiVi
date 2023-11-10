package com.poly.service;

import com.poly.dto.BillProRes;
import com.poly.dto.SearchStaffDto;
import com.poly.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public void add(BillProRes customer) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    Customer save(Customer customer);

    void deleteById(Integer id);

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    Customer findByEmail(String email);


    Customer getCustomerByName(String username);

    Page<Customer> loadData(SearchStaffDto searchStaffDto, Pageable pageable);
}
