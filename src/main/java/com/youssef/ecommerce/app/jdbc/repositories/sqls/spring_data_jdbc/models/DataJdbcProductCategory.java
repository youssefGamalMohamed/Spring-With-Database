package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table("products_categories")
public class DataJdbcProductCategory {

    @Id
    @Column("product_id")
    private Integer productId;

    @Column("category_id")
    private Integer categoryId;

}
