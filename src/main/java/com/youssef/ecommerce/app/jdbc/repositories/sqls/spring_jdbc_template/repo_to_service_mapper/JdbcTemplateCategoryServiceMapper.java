package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.repo_to_service_mapper;

import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models.JdbcTemplateCategory;
import com.youssef.ecommerce.app.jdbc.services.models.Category;

public class JdbcTemplateCategoryServiceMapper {

    public static Category toServiceModel(JdbcTemplateCategory jdbcTemplateCategory) {
        return Category.builder()
                .id(jdbcTemplateCategory.getId())
                .name(jdbcTemplateCategory.getName())
                .build();
    }
}
