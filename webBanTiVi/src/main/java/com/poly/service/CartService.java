package com.poly.service;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    public List<CartProduct> getitems();

    public List<Cart> getAll();

    public List<CartProduct> add(Integer id, Integer qty);

    public List<CartProduct> update(int id, Integer qty);

    public List<CartProduct> delete(Integer id);

    public void clear();

    public int getTotal();

    public int getTotalProduct();

    public BigDecimal getAmount();

    Cart getOne(Integer id);
}
