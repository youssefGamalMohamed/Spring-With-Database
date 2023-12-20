package com.youssef.ecommerce.app.jdbc.entities;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductCategory {

    private Integer productId;
    private Integer categoryId;

}
