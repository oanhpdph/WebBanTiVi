package com.poly.entity;

import com.poly.entity.idClass.CouponProductId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "discount_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CouponProductId.class)
public class CouponProduct {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_discount")
    private Coupon coupon;

    @Id
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;

    @Temporal(TemporalType.DATE)
    @Column(name="date_start")
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @Column(name="date_end")
    private Date dateEnd;

    private Integer status;
}
