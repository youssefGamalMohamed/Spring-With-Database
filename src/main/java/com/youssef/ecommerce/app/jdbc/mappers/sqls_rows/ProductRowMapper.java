package com.youssef.ecommerce.app.jdbc.mappers.sqls_rows;


import com.youssef.ecommerce.app.jdbc.entities.Product;
import com.youssef.ecommerce.app.jdbc.repositories.implementations.ProductRepoImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Product.builder()
                .id(rs.getInt(ProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME))
                .name(rs.getString(ProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME))
                .price(rs.getDouble(ProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.PRICE_COLUMN_NAME))
                .quantity(rs.getInt(ProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.QUANTITY_COLUMN_NAME))
                .build();
    }
}
