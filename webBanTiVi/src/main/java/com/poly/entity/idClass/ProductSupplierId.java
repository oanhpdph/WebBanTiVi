package com.poly.entity.idClass;

import com.poly.entity.Product;
import com.poly.entity.Supplier;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ProductSupplierId implements Serializable {
    private Supplier supplier;
    private Product product;
}
