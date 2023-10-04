package com.poly.repository;

import com.poly.entity.Voucher_Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Voucher_CustomerRepository extends JpaRepository<Voucher_Customer, Integer> {
}
