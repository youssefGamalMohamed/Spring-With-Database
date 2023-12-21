package com.youssef.ecommerce.app.jdbc.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table("categories")
public class Category {

    @Id
    @Column("category_id")
    private Integer id;
    private String name;

    @Transient // to ignore it from the relation because i want the relation to be created in the "productsIds" down below
    private Set<Product> products;

    // this for connecting the relation between the categories table and products_categories table
    // so that the ids of the table for the connected categories is associated with the products
    @JsonIgnore // because it's only needed in the data layer and not in the presentation layer
    @MappedCollection(idColumn = "category_id" , keyColumn = "product_id")
    private Set<ProductCategory> productsIds;

}
