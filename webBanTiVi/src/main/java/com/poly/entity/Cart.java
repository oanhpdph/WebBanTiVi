package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @Column(name = "code")
    private String code;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> listCartPro;
}
