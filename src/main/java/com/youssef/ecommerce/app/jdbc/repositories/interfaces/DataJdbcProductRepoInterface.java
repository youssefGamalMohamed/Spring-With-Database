package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Product;
import com.youssef.ecommerce.app.jdbc.mappers.sqls_rows.ProductWithCategoriesRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataJdbcProductRepoInterface extends CrudRepository<Product,Integer> {


    boolean existsByNameIgnoreCase(String productName);


    @Query(value =
            """
            SELECT
            	p.* , c.*
            FROM
                products p\s
                           \s
            INNER JOIN
            	products_categories pc\s
            
            ON
            	p.product_id = pc.product_id
                               \s
            INNER JOIN
            	categories c
            
            ON
            	c.category_id = pc.category_id
            
            WHERE
            	p.product_id = :id
            """

            ,
            rowMapperClass =  ProductWithCategoriesRowMapper.class
    )
    Product findProductByIdWithCategories(@Param("id") Integer id);
}
