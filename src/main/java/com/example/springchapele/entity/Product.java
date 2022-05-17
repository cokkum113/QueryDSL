package com.example.springchapele.entity;

import com.example.springchapele.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "product")
public class Product extends DefaultEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
