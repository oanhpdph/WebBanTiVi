package com.poly.service;

import com.poly.entity.Customer;
import com.poly.entity.Staff;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    void deleteById(Integer id);

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);


}
