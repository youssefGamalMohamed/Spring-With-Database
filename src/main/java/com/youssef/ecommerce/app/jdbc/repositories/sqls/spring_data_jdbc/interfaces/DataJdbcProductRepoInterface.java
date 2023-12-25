package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.models.DataJdbcProduct;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jdbc.sqls_row_mappers.DataJdbcProductWithCategories;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJdbcProductRepoInterface extends CrudRepository<DataJdbcProduct,Integer> {


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
            rowMapperClass =  DataJdbcProductWithCategories.class
    )
    DataJdbcProduct findProductByIdWithCategories(@Param("id") Integer id);
}
