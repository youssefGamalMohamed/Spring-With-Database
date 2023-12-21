package com.youssef.ecommerce.app.jdbc.entities;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table("products")
public class Product {

    @Id
    @Column("product_id")
    private Integer id;

    private String name;

    private Integer quantity;

    private Double price;


    @Transient
    private Set<Category> categories;
}
