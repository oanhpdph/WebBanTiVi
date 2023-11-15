package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "group_product")
    private GroupProduct groupProduct;

    @Column(name = "name_product")
    private String nameProduct;

    @Column(name = "sku")
    private String sku;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "avg_point")
    private float avgPoint;

    @Column(name = "same_product")
    private String same;

    @OneToMany(mappedBy = "product")
    private List<Evaluate> listEvaluate;

}