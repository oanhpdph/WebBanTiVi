package com.poly.service;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;

import java.util.Date;
import java.util.List;

public interface CartService {

    public List<CartProduct> getitems();

    public List<Cart> getAll();

    public void add(Integer id, Date date);

    public void update(int id, int qty);

    public void delete(int id);

    public void clear();

    public int getTotal();

    public int getAmount();
}
