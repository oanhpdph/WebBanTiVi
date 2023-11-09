package com.poly.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sku")
    private String sku;

   @ManyToOne
   @JoinColumn(name = "type")
    private TypeProduct type;

   @Column(name = "name_product")
   private String nameProduct;

    @Column(name = "price_import")
    private BigDecimal priceImport;

    @Column(name = "price_export")
    private BigDecimal priceExport;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "avg_point")
    private float avgPoint;

    @Column(name = "active")
    private boolean active;

    @Column(name = "description")
    private String description;

    @Column(name = "same_product")
    private String sameProduct;


    @JsonManagedReference
    @OneToMany(mappedBy = "productDetail", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ProductFieldValue> productFieldValues;
}
