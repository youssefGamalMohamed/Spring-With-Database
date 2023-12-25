package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.implementations;


import com.youssef.ecommerce.app.jdbc.entities.ProductCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.interfaces.JdbcTemplateProductCategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Set;

@Repository
public class JdbcTemplateJdbcTemplateProductCategoryRepoImpl implements JdbcTemplateProductCategoryRepoInterface {


    public static class PRODUCT_CATEGORY_TABLE {
        public static String TABLE_NAME = "products_categories";

        public static class COLUMNS_NAMES {
            public static String PRODUCT_ID_COLUMN_NAME = "product_id";
            public static String CATEGORY_ID_COLUMN_NAME = "category_id";
        }
    }

    public static class SQL_QUERIES {
        public static Integer BATCH_SIZE = 100;
        public static String SQL_SAVE_ROW = " INSERT INTO " + PRODUCT_CATEGORY_TABLE.TABLE_NAME + "(" + PRODUCT_CATEGORY_TABLE.COLUMNS_NAMES.PRODUCT_ID_COLUMN_NAME + "," + PRODUCT_CATEGORY_TABLE.COLUMNS_NAMES.CATEGORY_ID_COLUMN_NAME + ") VALUES(?,?)";

        public static String SQL_DELETE_ALL_ROWS_WITH_CATEGORY_ID = " DELETE FROM " + PRODUCT_CATEGORY_TABLE.TABLE_NAME + " WHERE  " + PRODUCT_CATEGORY_TABLE.COLUMNS_NAMES.CATEGORY_ID_COLUMN_NAME + " = ?";

        public static String SQL_DELETE_ALL_ROWS_WITH_PRODUCT_ID = " DELETE FROM " + PRODUCT_CATEGORY_TABLE.TABLE_NAME + " WHERE " + PRODUCT_CATEGORY_TABLE.COLUMNS_NAMES.PRODUCT_ID_COLUMN_NAME + " = ?";
    }


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void assignCategoriesToProduct(Integer productId, Set<Integer> categoriesIds) {
        categoriesIds.forEach(categoryId -> {
            jdbcTemplate.update(SQL_QUERIES.SQL_SAVE_ROW , productId , categoryId);
        });
    }

    @Override
    public void assignCategoriesToProduct(Set<JdbcTemplateProductCategory> productCategories) {
        jdbcTemplate.batchUpdate(
                SQL_QUERIES.SQL_SAVE_ROW,
                productCategories,
                SQL_QUERIES.BATCH_SIZE,
                (PreparedStatement ps, JdbcTemplateProductCategory productCategory) -> {
                    ps.setInt(1,  productCategory.getProductId());
                    ps.setInt(2,  productCategory.getCategoryId());
                }
        );
    }

    @Override
    public boolean deleteAllByCategoryId(Integer categoryId) {
        int deletionStatus = jdbcTemplate.update(SQL_QUERIES.SQL_DELETE_ALL_ROWS_WITH_CATEGORY_ID , categoryId);
        return (deletionStatus == 1);
    }

    @Override
    public boolean deleteAllByProductId(Integer productId) {
        return (jdbcTemplate.update(SQL_QUERIES.SQL_DELETE_ALL_ROWS_WITH_PRODUCT_ID , productId) == 1);
    }


}
