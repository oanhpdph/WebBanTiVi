package com.poly.repository;

import com.poly.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);

//    Customer findByEmail(String email);

    @Query(value = "select c from Customer c where c.email=?1")
    Optional<Customer> checkEmail(String email);

    @Query(value = "select c from Customer c where c.id=?1")
    Optional<Customer> findId(Integer id);

}
