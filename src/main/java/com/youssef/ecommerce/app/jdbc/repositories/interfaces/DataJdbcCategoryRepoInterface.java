package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.mappers.sqls_rows.CategoryRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;


@Repository
public interface DataJdbcCategoryRepoInterface extends CrudRepository<Category, Integer> {


    boolean existsByNameIgnoreCase(String categoryName);


    @Query(value = "SELECT * FROM categories WHERE category_id = :c_id LIMIT 1", rowMapperClass = CategoryRowMapper.class)
    Optional<Category> findById(@Param("c_id") Integer c_id);

    @Modifying
    @Query("""
                UPDATE categories
                SET name = :newCategoryName
                WHERE category_id = :categoryId
            """)
    void updateCategoryById(@Param("categoryId") Integer categoryId, @Param("newCategoryName") String newCategoryName);
}
