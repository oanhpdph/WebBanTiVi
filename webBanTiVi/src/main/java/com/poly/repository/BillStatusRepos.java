package com.poly.repository;

import com.poly.entity.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillStatusRepos extends JpaRepository<BillStatus,Integer> {
}
