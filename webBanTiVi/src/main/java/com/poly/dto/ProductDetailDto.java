package com.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDetailDto {
    Integer id;
    String sku = "";
    String nameProduct;
    Integer group = 0;
    boolean active;
    String sameProduct;
    List<ProductDetailListDto> listProduct;
    List<Attribute> product;

}
