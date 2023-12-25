package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JdbcTemplateProductCategory {

    private Integer productId;
    private Integer categoryId;

}
