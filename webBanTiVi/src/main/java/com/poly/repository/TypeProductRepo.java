package com.poly.repository;

import com.poly.entity.GroupProduct;
import com.poly.entity.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeProductRepo extends JpaRepository<TypeProduct, Integer> {
    @Query(value = "select t from TypeProduct t where t.groupProduct =?1" )
    List<TypeProduct> findByGroupProduct(GroupProduct id);
}
