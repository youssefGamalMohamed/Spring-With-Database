package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models;


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
@Table("products")
public class DataJdbcProduct {

    @Id
    @Column(value = TABLE.COLUMNS.ID)
    private Integer id;

    @Column(value = TABLE.COLUMNS.NAME)
    private String name;

    @Column(value = TABLE.COLUMNS.QUANTITY)
    private Integer quantity;

    @Column(value = TABLE.COLUMNS.PRICE)
    private Double price;


    @Transient
    private Set<DataJdbcCategory> categories;

    @MappedCollection(idColumn = "product_id" , keyColumn = "category_id")
    private Set<DataJdbcProductCategory> categoriesIds;

    public static class TABLE {
        public static class COLUMNS {
            public static final String ID = "product_id";
            public static final String NAME = "name";
            public static final String QUANTITY = "quantity";
            public static final String PRICE = "price";
        }
    }
}
