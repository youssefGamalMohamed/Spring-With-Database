package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.sqls_row_mappers;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations.JdbcTemplateCategoryRepoImpl;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations.JdbcTemplateProductRepoImpl;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

public class JdbcTemplateProductWithCategoriesRowMapper implements RowMapper<JdbcTemplateProduct> {

    @Override
    public JdbcTemplateProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<Integer,String> columnIndexesWithNames = Map.of(
                1, JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME,
                2,JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME,
                3,JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.QUANTITY_COLUMN_NAME,
                4,JdbcTemplateProductRepoImpl.PRODUCT_TABLE.COLUMNS_NAMES.PRICE_COLUMN_NAME,
                5, JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME,
                6,JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME
        );
        JdbcTemplateProduct product = JdbcTemplateProduct.builder()
                .categories(new HashSet<>())
                .build();


        do {

            product.setId(rs.getInt(columnIndexesWithNames.get(1)));
            product.setName(rs.getString(columnIndexesWithNames.get(2)));
            product.setPrice(rs.getDouble(columnIndexesWithNames.get(4)));
            product.setQuantity(rs.getInt(columnIndexesWithNames.get(3)));

            JdbcTemplateCategory category = JdbcTemplateCategory.builder()
                    .id(rs.getInt(columnIndexesWithNames.get(5)))
                    .name(rs.getString(6))
                    .products(new HashSet<>())
                    .build();

            product.getCategories()
                    .add(category);

        } while (rs.next());


        return product;
    }
}
