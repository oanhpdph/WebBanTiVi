package com.poly.repository;

import com.poly.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,Integer> {
    @Query(value = "select b from Voucher b where b.id = ?1 ")
    Optional<Voucher> getVoucherById(Integer id);


}
