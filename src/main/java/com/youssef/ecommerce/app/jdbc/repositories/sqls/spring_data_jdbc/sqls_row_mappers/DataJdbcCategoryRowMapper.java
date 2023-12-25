package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.sqls_row_mappers;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataJdbcCategoryRowMapper implements RowMapper<DataJdbcCategory> {

    @Override
    public DataJdbcCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DataJdbcCategory.builder()
                .id(rs.getInt(DataJdbcCategory.TABLE.COLUMNS.ID))
                .name(rs.getString(DataJdbcCategory.TABLE.COLUMNS.NAME))
                .build();
    }

}
