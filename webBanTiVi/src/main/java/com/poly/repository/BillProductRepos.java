package com.poly.repository;

import com.poly.entity.BillProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillProductRepos extends JpaRepository<BillProduct, Integer> {
}
