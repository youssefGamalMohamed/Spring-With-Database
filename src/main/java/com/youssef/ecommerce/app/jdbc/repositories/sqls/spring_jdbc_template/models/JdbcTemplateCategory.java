package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_jdbc_template.models;


import lombok.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class JdbcTemplateCategory {

    private Integer id;
    private String name;
    private Set<JdbcTemplateProduct> products;

}
