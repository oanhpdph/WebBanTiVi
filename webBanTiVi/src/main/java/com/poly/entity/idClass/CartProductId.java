package com.poly.entity.idClass;

import com.poly.entity.Cart;
import com.poly.entity.ProductDetail;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class CartProductId implements Serializable {
    private ProductDetail product;
    private Cart cart;
}
