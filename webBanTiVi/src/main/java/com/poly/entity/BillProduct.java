package com.poly.entity;

import com.poly.entity.idClass.BillProductId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bill_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(BillProductId.class)
public class BillProduct {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="price")
    private BigDecimal price;

}
