package com.poly.service;

import com.poly.dto.ProductDetailDto;
import com.poly.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> saveList(List<ProductDetailDto> dto);

    List<ProductDetail> getSameProduct(String sameCode);

    ProductDetail findById(Integer id);

    Page<ProductDetail> findAll(ProductDetailDto productDetailDto, Pageable pageable);

    Boolean delete(Integer id);

    List<ProductDetail> update(List<ProductDetailDto> productDetailDto);
}
