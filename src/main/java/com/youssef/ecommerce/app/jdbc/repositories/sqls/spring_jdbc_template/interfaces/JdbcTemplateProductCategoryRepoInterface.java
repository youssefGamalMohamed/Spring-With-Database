package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.interfaces;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateProductCategory;

import java.util.Set;

public interface JdbcTemplateProductCategoryRepoInterface {

    void assignCategoriesToProduct(Integer productId , Set<Integer> categoriesIds);

    void assignCategoriesToProduct(Set<JdbcTemplateProductCategory> productCategories);

    boolean deleteAllByCategoryId(Integer categoryId);

    boolean deleteAllByProductId(Integer productId);
}
