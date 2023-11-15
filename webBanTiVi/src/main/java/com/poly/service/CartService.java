package com.poly.service;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;

import java.io.Serializable;
import java.util.List;

public interface CartService {

    List<CartProduct> getitems();

    List<Cart> getAll();

    Cart save(Cart cart);

    List<CartProduct> add(Integer id, Integer qty);

    List<CartProduct> update(int id, Integer qty);

    List<CartProduct> delete(Integer id);

    void clear();

    int getTotal();

    int getTotalProduct();

    Serializable getAmount();

    Cart getOneByUser(Integer id);
}
