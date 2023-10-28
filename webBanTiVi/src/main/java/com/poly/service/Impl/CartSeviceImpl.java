package com.poly.service.Impl;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;
import com.poly.entity.Product;
import com.poly.repository.CartRepos;
import com.poly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CartSeviceImpl implements CartService {
    @Autowired
    CartRepos cartRepos;
    @Autowired
    ProductServiceImpl productService;

    List<CartProduct> items = new ArrayList<>();

    @Override
    public List<CartProduct> getitems() {
        return items;
    }

    @Override
    public List<Cart> getAll() {
        return cartRepos.findAll();
    }

    @Override
    public void add(Integer id, Date creDate) {
        CartProduct item = items
                .stream()
                .filter(it -> it.getProduct().getId() == id)
                .findFirst()
                .orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            return;
        }
//        Date creDate = item.getCreateDate();
        Cart cart = new Cart();
        Product product = productService.findById(id);
        if (product != null) {
            items.add(
                    new CartProduct(product, cart, 1, null, creDate, cart.getDateUpdate())
            );
        }
    }

    @Override
    public void update(int id, int qty) {
        CartProduct item = items
                .stream()
                .filter(it -> it.getProduct().getId() == id)
                .findFirst()
                .orElse(null);
        if (items != null) {
            item.setQuantity(qty);
        }
    }

    @Override
    public void delete(int id) {
        items = items
                .stream()
                .filter(it -> it.getProduct().getId() != id)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public int getTotal() {
        int total = 0;
        for (CartProduct item : items) {
            total += item.getQuantity();
        }
        return total;
    }

    @Override
    public int getAmount() {
        int total = 0;
//        for (CartProduct item : items) {
//            total += item.getQuantity() * item.getProduct().getPrice_export();
//        }
        return total;
    }


}
