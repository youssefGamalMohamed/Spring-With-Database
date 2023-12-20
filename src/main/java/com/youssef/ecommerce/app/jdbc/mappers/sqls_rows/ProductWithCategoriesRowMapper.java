package com.youssef.ecommerce.app.jdbc.mappers.sqls_rows;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.entities.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ProductWithCategoriesRowMapper implements RowMapper<Product> {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {


        Map<Integer,String> columnIndexesWithNames = Map.of(
                1,"product_id",
                2,"name",
                3,"quantity",
                4,"price",
                5,"category_id",
                6,"name"
        );

        Product product = Product.builder()
                .build();

        while (rs.next()) {
            product.setId(rs.getInt(columnIndexesWithNames.get(1)));
            product.setName(rs.getString(columnIndexesWithNames.get(2)));
            product.setPrice(rs.getDouble(columnIndexesWithNames.get(4)));
            product.setQuantity(rs.getInt(columnIndexesWithNames.get(3)));
            product.setCategories(new HashSet<>());

            Category category = Category.builder()
                    .id(rs.getInt(columnIndexesWithNames.get(5)))
                    .name(rs.getString(columnIndexesWithNames.get(6)))
                    .products(new HashSet<>())
                    .build();

            product.getCategories()
                    .add(category);
        }

        return product;
    }
}
