package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "image_returned")
@AllArgsConstructor
@NoArgsConstructor
public class Image_returned {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_bill_product")
    private BillProduct billProduct;

    @Column(name = "name_image")
    private String nameImage;
}
