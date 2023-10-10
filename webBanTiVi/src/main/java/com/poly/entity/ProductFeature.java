package com.poly.entity;

import com.poly.entity.idClass.ProductFeatureId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_feature")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductFeatureId.class)
public class ProductFeature {
    @Id
    @ManyToOne
    @JoinColumn(name="id_feature")
    private Feature feature;

    @Id
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
}
