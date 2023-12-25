package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepo extends JpaRepository<JpaProduct,Integer> {
}
