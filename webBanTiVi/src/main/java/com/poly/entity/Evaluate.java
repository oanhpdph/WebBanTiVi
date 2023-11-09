package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "evaluate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_product")
    private ProductDetail product;

    @ManyToOne
    @JoinColumn(name="id_user")
    private Customer customer;

    @Temporal(TemporalType.DATE)
    @Column(name="date_create")
    private Date dateCreate;

    @Column(name="point")
    private Integer point;

    @Column(name="comment")
    private String comment;
}
