package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="voucher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name_voucher")
    private String name_voucher;

    @Column(name="value")
    private int value;

    @Column(name="reduced_form")
    private double reduced_form;

    @Column(name="minimum_value")
    private double minimum_value;


    @Column(name="maximum_discount")
    private double maximum_discount;

    @Column(name="quantity")
    private int quantity;

    @Column(name="start_day")
    private Date start_day;

    @Column(name="expiration_date")
    private Date exDate;


    @Column(name="active")
    private boolean active;


}
