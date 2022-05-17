package com.example.springchapele.entity;

import com.example.springchapele.DefaultEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "`order`")
public class Order extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer amount;

}
