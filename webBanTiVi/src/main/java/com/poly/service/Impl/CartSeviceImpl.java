package com.poly.service.Impl;

import com.poly.entity.Cart;
import com.poly.entity.CartProduct;
import com.poly.entity.Product;
import com.poly.repository.CartRepos;
import com.poly.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
    public List<CartProduct> add(Integer id) {

        CartProduct item = items
                .stream()
                .filter(it -> it.getProduct().getId() == id)
                .findFirst()
                .orElse(null);
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            return items;
        }
        Cart cart = new Cart();
        Product product = productService.findById(id);
        if (product != null) {
            items.add(
                    new CartProduct(product, cart, 1, null, new Date(), cart.getDateUpdate())
            );
        }
        return items;
    }

    @Override
    public void update(int id, Integer qty) {
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
    public int getTotalProduct() {
        int total = 0;
        for (CartProduct item : items) {
//            total += item.getQuantity() * item.getProduct().getPrice_export();
        }
        return total;
    }

    @Override
    public Serializable getAmount() {
        BigDecimal amount = null;
        for (CartProduct item : items) {
            BigDecimal qty = new BigDecimal(item.getQuantity());
            BigDecimal price = new BigDecimal(String.valueOf(item.getProduct().getPrice_export()));
            BigDecimal result = qty.multiply(price);
            amount = result.setScale(2, RoundingMode.HALF_EVEN);
        }
        return amount;
    }
    @Override
    public Cart getOne(Integer id) {
        return cartRepos.findById(id).get();
    }
//    @Override
//    public Serializable getAmount() {
//        int amount = 0;
//        for (CartProduct item : items) {
//            amount += item.getQuantity() * item.getProduct().getPrice_export();
//        }
//        return amount;
//    }
}
