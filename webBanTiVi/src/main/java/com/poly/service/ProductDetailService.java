package com.poly.service;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.Product;
import com.poly.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    Product saveList(ProductDetailDto dto);

    ProductDetail findById(Integer id);

    Page<ProductDetail> findAll(ProductDetailDto productDetailDto, Pageable pageable);

    List<ProductDetail> findAll();

    Boolean delete(Integer id);

    List<ProductDetail> update(List<ProductDetailDto> productDetailDto);
}
