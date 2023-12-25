package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer quantity;
    private Integer price;

    @ManyToMany(mappedBy = "jpaProducts")
    private Set<JpaCategory> categories;
}
