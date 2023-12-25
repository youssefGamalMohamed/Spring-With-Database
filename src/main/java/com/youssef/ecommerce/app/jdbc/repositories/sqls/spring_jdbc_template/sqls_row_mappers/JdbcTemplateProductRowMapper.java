package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.sqls_row_mappers;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations.JdbcTemplateProductRepoImpl;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProduct;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateProductRowMapper implements RowMapper<JdbcTemplateProduct> {
    @Override
    public JdbcTemplateProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JdbcTemplateProduct.builder()
                .id(rs.getInt(JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME))
                .name(rs.getString(JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME))
                .price(rs.getDouble(JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.PRICE_COLUMN_NAME))
                .quantity(rs.getInt(JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.QUANTITY_COLUMN_NAME))
                .build();
    }
}
