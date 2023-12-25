package com.youssef.ecommerce.app.jdbc.repositories.implementations;

import com.youssef.ecommerce.app.jdbc.services.models.Product;
import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class JdbcTemplateProductRepoImpl implements ProductRepoInterface {


    public static class PRODUCT_TABLE {
        public static String TABLE_NAME = "products";

        public static class COLUMNS_NAMES {
            public static String ID_COLUMN_NAME = "product_id";
            public static String NAME_COLUMN_NAME = "name";
            public static String QUANTITY_COLUMN_NAME = "quantity";
            public static String PRICE_COLUMN_NAME = "price";
        }
    }

    public static class SQL_QUERIES {
        public static String SQl_COUNT_PRODUCTS_WITH_NAME_IGNORE_CASE = " SELECT COUNT(*) AS productCountWithSpecificNameIgnoreCase FROM " + PRODUCT_TABLE.TABLE_NAME + " WHERE LOWER(" + PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + ") = LOWER(?) ";
        public static String SQL_SAVE_PRODUCT = " INSERT INTO " + PRODUCT_TABLE.TABLE_NAME + "(" + PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + "," + PRODUCT_TABLE.COLUMNS_NAMES.PRICE_COLUMN_NAME + "," + PRODUCT_TABLE.COLUMNS_NAMES.QUANTITY_COLUMN_NAME + ")" + " VALUES(?,?,?) ";

        public static String SQL_FIND_PRODUCT_BY_ID = " SELECT * FROM " + PRODUCT_TABLE.TABLE_NAME + " WHERE " + PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ? ";

        public static String SQL_FIND_PRODUCT_BY_ID_WITH_CATEGORIES_ASSOCIATED_WITH_IT = """
                SELECT
                	p.* , c.*
                FROM
                """
                + PRODUCT_TABLE.TABLE_NAME + " p "
                + """
                INNER JOIN
                """ +
                JdbcTemplateProductCategoryRepoImpl.PRODUCT_CATEGORY_TABLE.TABLE_NAME + " pc "
                +
                """
                                                    
                        ON\s
                        	p.product_id = pc.product_id
                        INNER JOIN
                        """
                +
                JdbcTemplateCategoryRepoImpl.CATEGORY_TABLE.TABLE_NAME + " c "
                +
                """
                            ON
                            	c.category_id = pc.category_id
                            WHERE\s
                            	p.product_id = ?;
                        """;

        public static String SQL_DELETE_PRODUCT_BY_ID = " DELETE FROM " + PRODUCT_TABLE.TABLE_NAME + " WHERE " + PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ?";

        public static String SQL_UPDATE_PRODUCT_BY_ID = " UPDATE " + PRODUCT_TABLE.TABLE_NAME + " SET " + PRODUCT_TABLE.COLUMNS_NAMES.NAME_COLUMN_NAME + " = ? , " +
                PRODUCT_TABLE.COLUMNS_NAMES.QUANTITY_COLUMN_NAME + " = ? , " + PRODUCT_TABLE.COLUMNS_NAMES.PRICE_COLUMN_NAME + " = ? "
                + " WHERE " + PRODUCT_TABLE.COLUMNS_NAMES.ID_COLUMN_NAME + " = ? ";

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplateProductCategoryRepoImpl jdbcTemplateProductCategoryRepo;

    @Override
    public boolean isExistProductByNameIgnoreCase(String productName) {
        // Query to count products with the given name (ignoring case)
        Integer count = jdbcTemplate.queryForObject(
                SQL_QUERIES.SQl_COUNT_PRODUCTS_WITH_NAME_IGNORE_CASE,
                Integer.class,
                productName
        );

        if (count == null)
            return false;

        return count > 0;
    }

    @Override
    public Product save(Product product) {

//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(
//                conn -> {
//                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_QUERIES.SQL_SAVE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
//                    preparedStatement.setString(1, product.getName());
//                    preparedStatement.setDouble(2, product.getPrice());
//                    preparedStatement.setInt(3, product.getQuantity());
//                    log.info(">>>>> SQL = " + preparedStatement.toString());
//                    return preparedStatement;
//                }
//                , keyHolder
//        );
//
//        product.setId(keyHolder.getKey().intValue());
//
//        Set<Integer> categoriesIds = product.getCategories()
//                .stream()
//                .map(Category::getId)
//                .collect(Collectors.toSet());
//
//        jdbcTemplateProductCategoryRepo.assignCategoriesToProduct(product.getId() , categoriesIds);
//        return product;

        return null;
    }

    @Override
    public Product findById(Integer productId) {
//        Product product = null;
//        try {
//            product = jdbcTemplate.queryForObject(
//                    SQL_QUERIES.SQL_FIND_PRODUCT_BY_ID,
//                    new ProductRowMapper(),
//                    productId
//            );
//        } catch (Exception e) {
//            product = null;
//        }
//        return product;

        return null;
    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
//        Product product = null;
//        try {
//            product = jdbcTemplate.queryForObject(
//                    SQL_QUERIES.SQL_FIND_PRODUCT_BY_ID_WITH_CATEGORIES_ASSOCIATED_WITH_IT,
//                    new ProductWithCategoriesRowMapper(),
//                    productId
//            );
//        } catch (Exception e) {
//            log.error(">>>>> ERROR WITH SQL STATEMENT EXECUTION = " + e.getMessage());
//            product = null;
//        }
//        return product;

        return null;
    }

    @Override
    public boolean deleteById(Integer productId) {
        return (jdbcTemplate.update(SQL_QUERIES.SQL_DELETE_PRODUCT_BY_ID, productId) == 1);
    }

    @Override
    public boolean updateProductById(Integer productId, Product product) {
        return (jdbcTemplate.update(SQL_QUERIES.SQL_UPDATE_PRODUCT_BY_ID ,  product.getName() , product.getQuantity() , product.getPrice() , productId) == 1);
    }
}
