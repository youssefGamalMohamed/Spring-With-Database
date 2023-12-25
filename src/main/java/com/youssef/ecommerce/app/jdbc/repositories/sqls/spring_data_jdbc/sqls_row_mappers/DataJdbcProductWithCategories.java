package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.sqls_row_mappers;


import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class DataJdbcProductWithCategories implements RowMapper<DataJdbcProduct> {

    @Override
    public DataJdbcProduct mapRow(ResultSet rs, int rowNum) throws SQLException {

        Map<Integer,String> columnIndexesWithNames = Map.of(
                1,DataJdbcProduct.TABLE.COLUMNS.ID,
                2,DataJdbcProduct.TABLE.COLUMNS.NAME,
                3,DataJdbcProduct.TABLE.COLUMNS.QUANTITY,
                4,DataJdbcProduct.TABLE.COLUMNS.PRICE,
                5,DataJdbcCategory.TABLE.COLUMNS.ID,
                6,DataJdbcCategory.TABLE.COLUMNS.NAME
        );

        DataJdbcProduct dataJdbcProduct = DataJdbcProduct.builder()
                .categories(new HashSet<>())
                .categoriesIds(new HashSet<>())
                .build();

        do {

            dataJdbcProduct.setId(rs.getInt(columnIndexesWithNames.get(1)));
            dataJdbcProduct.setName(rs.getString(columnIndexesWithNames.get(2)));
            dataJdbcProduct.setPrice(rs.getDouble(columnIndexesWithNames.get(4)));
            dataJdbcProduct.setQuantity(rs.getInt(columnIndexesWithNames.get(3)));

            DataJdbcCategory dataJdbcCategory = DataJdbcCategory.builder()
                    .id(rs.getInt(columnIndexesWithNames.get(5)))
                    .name(rs.getString(6))
                    .products(new HashSet<>())
                    .build();

            dataJdbcProduct.getCategories()
                    .add(dataJdbcCategory);

        } while (rs.next());

        return dataJdbcProduct;
    }


}
