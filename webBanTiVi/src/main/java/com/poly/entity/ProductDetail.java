package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sku")
    private String sku;

    @Column(name = "price_import")
    private BigDecimal priceImport;

    @Column(name = "price_export")
    private BigDecimal priceExport;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Image> listImage;


}
