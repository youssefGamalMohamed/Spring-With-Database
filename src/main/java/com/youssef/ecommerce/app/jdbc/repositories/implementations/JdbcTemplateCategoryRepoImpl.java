package com.youssef.ecommerce.app.jdbc.repositories.implementations;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.mappers.sqls_rows.CategoryRowMapper;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.CategoryRepoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcTemplateCategoryRepoImpl implements CategoryRepoInterface {


    public static class CATEGORY_TABLE {
        public static String TABLE_NAME = "categories";

        public static class COLUMNS_NAMES {
            public static String ID_COLUMN_NAME = "category_id";
            public static String NAME_COLUMN_NAME = "name";
        }
    }

    public static class SQL_QUERIES {
        public static String SQL_SAVE_CATEGORY = " INSERT INTO " + CATEGORY_TABLE.TABLE_NAME + "(" + CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + ") VALUES(?)";
        public static String SQl_COUNT_CATEGORIES_WITH_NAME_IGNORE_CASE = " SELECT COUNT(*) AS categoriesCountWithSpecificNameIgnoreCase FROM " + CATEGORY_TABLE.TABLE_NAME + " WHERE LOWER(" + CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + ") = LOWER(?) ";
        public static String SQl_COUNT_CATEGORIES_WITH_ID = " SELECT COUNT(*) FROM " + CATEGORY_TABLE.TABLE_NAME + " WHERE " + CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ?";

        public static String SQL_FIND_CATEGORY_BY_ID = "SELECT * FROM " + CATEGORY_TABLE.TABLE_NAME + " WHERE " + CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ?";

        public static String SQL_DELETE_CATEGORY_BY_ID = " DELETE FROM " + CATEGORY_TABLE.TABLE_NAME + " WHERE " + CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ?";

        public static String SQL_UPDATE_CATEGORY_BY_ID = " UPDATE  " + CATEGORY_TABLE.TABLE_NAME + " SET " + CATEGORY_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + " = ? " + " WHERE " + CATEGORY_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ? " ;
    }




    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isExistCategoryByNameIgnoreCase(String categoryName) {
        // Query to count categories with the given name (ignoring case)
        Integer count = jdbcTemplate.queryForObject(
                SQL_QUERIES.SQl_COUNT_CATEGORIES_WITH_NAME_IGNORE_CASE,
                Integer.class,
                categoryName
        );

        if (count == null)
            return false;

        return count > 0;
    }

    @Override
    public Category saveCategory(Category category) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                conn -> {
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERIES.SQL_SAVE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, category.getName());
                    log.info(">>>>> SQL = " + preparedStatement.toString());
                    return preparedStatement;
                }
                , keyHolder
        );

        category.setId(keyHolder.getKey().intValue());
        return category;
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {

        // Query to count categories with the given id
        Integer count = jdbcTemplate.queryForObject(
                SQL_QUERIES.SQl_COUNT_CATEGORIES_WITH_ID,
                Integer.class,
                categoryId
        );

        if (count == null)
            return false;

        return true;
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Category category = null;
        try {
            category = jdbcTemplate.queryForObject(SQL_QUERIES.SQL_FIND_CATEGORY_BY_ID , new CategoryRowMapper() , categoryId);
        }
        catch (EmptyResultDataAccessException e) {

        }

        if(category == null)
            return Optional.empty();

        return Optional.of(category);
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        int isDeleted = jdbcTemplate.update(SQL_QUERIES.SQL_DELETE_CATEGORY_BY_ID , categoryId);
        return (isDeleted == 1);
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, Category category) {
        return (jdbcTemplate.update(SQL_QUERIES.SQL_UPDATE_CATEGORY_BY_ID , category.getName() , categoryId) == 1);
    }


}
