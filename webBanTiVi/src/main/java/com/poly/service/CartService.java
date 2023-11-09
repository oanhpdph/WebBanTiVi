package com.poly.service;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface CartService {

    public List<CartProduct> getitems();

    public List<Cart> getAll();

    public List<CartProduct> add(Integer id);

    public List<CartProduct> update(int id, Integer qty);

    public List<CartProduct> delete(Integer id);

    public void clear();

    public int getTotal();

    public int getTotalProduct();

    public Serializable getAmount();

    Cart getOne(Integer id);
}
