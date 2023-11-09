package com.poly.repository;

import com.poly.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BillProductRepos extends JpaRepository<BillProduct, Integer> {

    @Query(value = "select b from BillProduct b where b.bill.id = ?1 and b.product.id=?1")
    Optional<BillProduct> findByBill(Integer idBill, Integer idPro);

}
