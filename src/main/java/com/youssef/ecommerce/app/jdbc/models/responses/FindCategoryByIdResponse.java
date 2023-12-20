package com.youssef.ecommerce.app.jdbc.models.responses;


import com.youssef.ecommerce.app.jdbc.entities.Category;
import lombok.*;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FindCategoryByIdResponse {

    private Integer id;
    private String name;

}
