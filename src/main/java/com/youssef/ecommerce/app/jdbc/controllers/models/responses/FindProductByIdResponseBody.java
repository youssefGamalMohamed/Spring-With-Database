package com.youssef.ecommerce.app.jdbc.controllers.models.responses;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FindProductByIdResponseBody {

    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
}
