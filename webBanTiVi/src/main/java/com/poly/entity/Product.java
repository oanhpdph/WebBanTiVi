package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

    @Column(name = "code")
    private String code;

    @Column(name = "nametv")
    private String name;

    @Column(name = "price_import")
    private BigDecimal price_import;

    @Column(name = "price_export")
    private BigDecimal price_export;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_brand")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "id_origin")
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "id_manufacture")
    private Manufacture manufacture;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_resolution")
    private Resolution resolution;


    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<CouponProduct> couponProducts;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<ImageProduct> listImage;

    @OneToMany(mappedBy = "product")
    private List<Evaluate> listEvaluate;

    @OneToMany(mappedBy = "product")
    private Set<CartProduct> setCart;

    public String getCouponProduct() {
        List<CouponProduct> list = this.couponProducts;
        for (CouponProduct x : list
        ) {
            if (x.getStatus() == 1) {
                return x.getCoupon().getValue();
            }
        }
        return null;

    }

    public BigDecimal getCoupon() {
        if (this.getCouponProduct() != null) {
            String ptkm = this.getCouponProduct().replace("%", "");
            BigDecimal giasau = this.price_export.subtract(this.price_export.multiply(BigDecimal.valueOf(Double.parseDouble(ptkm))).divide(BigDecimal.valueOf(100)));
            return giasau;
        }
        return BigDecimal.ZERO;
    }

//    @OneToMany(mappedBy = "product")
//    private Set<Coupon> coupon;
}
