package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private Product product;

    @ManyToOne
    @JoinColumn(name="id_customer")
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_create")
    private Date dateCreate;

    @Column(name="point")
    private Integer point;

    @Column(name="comment")
    private String comment;
}
