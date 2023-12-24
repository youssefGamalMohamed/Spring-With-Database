package com.youssef.ecommerce.app.jdbc.mappers.sqls_rows;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.repositories.implementations.JdbcTemplateCategoryRepoImpl;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Category.builder()
                .id(rs.getInt(JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME))
                .name(rs.getString(JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME))
                .build();
    }
}
