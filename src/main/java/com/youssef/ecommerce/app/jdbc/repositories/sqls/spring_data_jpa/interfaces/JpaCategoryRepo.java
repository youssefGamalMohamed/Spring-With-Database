package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCategoryRepo extends JpaRepository<JpaCategory,Integer> {

    boolean existsByNameIgnoreCase(String name);
}
