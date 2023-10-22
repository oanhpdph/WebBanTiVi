package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "image_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "name_image")
    private String nameImage;

    @Column(name = "location")
    private boolean location;
}
