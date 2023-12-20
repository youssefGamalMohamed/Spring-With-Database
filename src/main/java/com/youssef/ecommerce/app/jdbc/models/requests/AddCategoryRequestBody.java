package com.youssef.ecommerce.app.jdbc.models.requests;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddCategoryRequestBody {
    private String name;
}
