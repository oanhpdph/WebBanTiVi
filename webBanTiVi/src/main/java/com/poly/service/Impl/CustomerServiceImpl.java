package com.poly.service.Impl;

import com.poly.entity.Customer;
import com.poly.repository.CustomerRepository;
import com.poly.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
     this.customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return this.customerRepository.findById(id);
    }

//    @Override
//    public Customer findByEmailAndPass(CustomerDto dto) {
//        Customer customer = null;
//       for(Customer cus : this.customerRepository.findAll()){
//           if(dto.getEmail().equals(cus.getEmail()) && dto.getPassword().equals(cus.getPassword())){
//               customer= cus;
//           }
//       }
//        return customer;
//    }


}
