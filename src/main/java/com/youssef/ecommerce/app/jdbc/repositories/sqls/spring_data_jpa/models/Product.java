package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private Integer id;
    private Integer quantity;
    private Integer price;

    @ManyToMany(mappedBy = "products")
    private Set<Category> categories;
}
