package com.poly.entity.idClass;

import com.poly.entity.Feature;
import com.poly.entity.Product;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ProductFeatureId implements Serializable {
    private Feature feature;
    private Product product;
}
