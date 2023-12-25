package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models;


import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {

    @Id
    private Integer id;
    private String name;

    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

}
