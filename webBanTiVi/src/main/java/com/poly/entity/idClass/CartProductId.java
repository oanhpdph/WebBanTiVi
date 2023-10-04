package com.poly.entity.idClass;

import com.poly.entity.Cart;
import com.poly.entity.Product;

import java.io.Serializable;

public class CartProductId implements Serializable {
    private Product product;
    private Cart cart;
}
