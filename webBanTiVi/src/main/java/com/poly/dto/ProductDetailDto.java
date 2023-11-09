package com.poly.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ProductDetailDto {
    Integer id;
    String sku="";
    String nameProduct;
    BigDecimal priceImport;
    BigDecimal priceExport;
    Integer quantity;
    Integer type = 0;
    List<ImageDto> image;
    List<Attribute> listAttributes;
    String sameProduct;
}
