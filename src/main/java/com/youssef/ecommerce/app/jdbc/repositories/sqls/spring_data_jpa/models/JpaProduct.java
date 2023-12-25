package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "products")
public class JpaProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;

    @ManyToMany(mappedBy = "jpaProducts", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Set<JpaCategory> categories = new HashSet<>();
}
