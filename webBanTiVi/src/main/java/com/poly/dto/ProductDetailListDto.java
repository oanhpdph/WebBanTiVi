package com.poly.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailListDto {
    List<ImageDto> image;
    List<Attribute> listAttributes;
    BigDecimal priceImport;
    BigDecimal priceExport;
    Integer quantity;
    String sku = "";
    boolean active;
}
