package com.poly.entity;

import com.poly.entity.idClass.ProductSupplierId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_supplier")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ProductSupplierId.class)
public class ProductSupplier {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    @Id
    @ManyToOne
    @JoinColumn(name="id_product")
    private Product product;
}
