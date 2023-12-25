package com.youssef.ecommerce.app.jdbc.controllers.models.requests;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddCategoryRequestBody {
    private String name;
}
