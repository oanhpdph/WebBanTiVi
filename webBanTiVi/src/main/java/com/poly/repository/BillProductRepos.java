package com.poly.repository;

import com.poly.entity.BillProduct;
import com.poly.entity.idClass.BillProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillProductRepos extends JpaRepository<BillProduct, Integer> {
}
