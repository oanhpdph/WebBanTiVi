package com.poly.service;

import com.poly.dto.CustomerDto;
import com.poly.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);

    void deleteById(Integer id);

    List<Customer> findAll();

    Optional<Customer> findById(Integer id);

    Customer findByEmailAndPass(CustomerDto dto);


}
