package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.math.BigDecimal;

@Entity
@Table(name="voucher")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name_voucher")
    private String name_voucher;

    @Column(name="value")
    private int value;

    @Column(name="reduced_form")
    private boolean reduced_form;

    @Column(name="minimum_value")
    private BigDecimal minimum_value;

    @Column(name="maximum_discount")
    private BigDecimal maximum_discount;

    @Column(name="quantity")
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_day")
    private Date startDay;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name="active")
    private Boolean active;

}
