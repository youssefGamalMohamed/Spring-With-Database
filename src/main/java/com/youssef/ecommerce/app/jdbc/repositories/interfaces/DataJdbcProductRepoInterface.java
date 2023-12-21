package com.youssef.ecommerce.app.jdbc.repositories.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataJdbcProductRepoInterface extends CrudRepository<Product,Integer> {


    boolean existsByNameIgnoreCase(String productName);
}
