package com.poly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="value")
    private String value;

    @Column(name="active")
    private boolean active;

    @Column(name = "image")
    private String image;
}
