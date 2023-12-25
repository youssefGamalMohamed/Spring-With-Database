package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.sqls_row_mappers;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations.JdbcTemplateCategoryRepoImpl;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplateCategoryRowMapper implements RowMapper<JdbcTemplateCategory> {

    @Override
    public JdbcTemplateCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JdbcTemplateCategory.builder()
                .id(rs.getInt(JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME))
                .name(rs.getString(JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME))
                .build();
    }

}
