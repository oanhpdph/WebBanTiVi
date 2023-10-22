package com.poly.entity.idClass;

import com.poly.entity.Bill;
import com.poly.entity.Product;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
@EqualsAndHashCode
public class BillProductId implements Serializable {
    private Product product;
    private Bill bill;
}
