package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.sqls_row_mappers.DataJdbcCategoryRowMapper;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DataJdbcCategoryRepoInterface extends CrudRepository<DataJdbcCategory, Integer> {


    boolean existsByNameIgnoreCase(String categoryName);


    @Query(value = "SELECT * FROM categories WHERE category_id = :c_id LIMIT 1", rowMapperClass = DataJdbcCategoryRowMapper.class)
    Optional<DataJdbcCategory> findById(@Param("c_id") Integer c_id);

    @Modifying
    @Query("""
                UPDATE categories
                SET name = :newCategoryName
                WHERE category_id = :categoryId
            """)
    void updateCategoryById(@Param("categoryId") Integer categoryId, @Param("newCategoryName") String newCategoryName);
}
