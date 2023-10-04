package com.poly.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resolution")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "nameresolution")
    private String nameResolution;

    @Column(name = "screen_length")
    private float screenLength;

    @Column(name = "screen_width")
    private float screenWidth;

}
