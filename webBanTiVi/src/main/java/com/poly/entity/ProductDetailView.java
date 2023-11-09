package com.poly.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Data
public class ProductDetailView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price_import;
    private BigDecimal price_export;
    private Integer quantity;
    private Brand brand;
    private Origin origin;
    private Manufacture manufacture;
    private Color color;
    private Type type;
    private Size size;
    private Resolution resolution;
    private List<ImageProduct> listImage;
    private List<Evaluate> listEvaluate;
    private Set<CartProduct> setCart;
    private Product product;

    public ProductDetailView(Integer id, String name, BigDecimal price_import, BigDecimal price_export, Integer quantity, Brand brand, Origin origin, Manufacture manufacture, Color color, Type type, Size size, Resolution resolution, List<ImageProduct> listImage, List<Evaluate> listEvaluate, Set<CartProduct> setCart, Product product) {
        this.id = id;
        this.name = name;
        this.price_import = price_import;
        this.price_export = price_export;
        this.quantity = quantity;
        this.brand = brand;
        this.origin = origin;
        this.manufacture = manufacture;
        this.color = color;
        this.type = type;
        this.size = size;
        this.resolution = resolution;
        this.listImage = listImage;
        this.listEvaluate = listEvaluate;
        this.setCart = setCart;
        this.product = product;
    }
    public ProductDetailView() {
    }
}
