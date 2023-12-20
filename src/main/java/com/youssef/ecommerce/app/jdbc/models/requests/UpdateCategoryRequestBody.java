package com.youssef.ecommerce.app.jdbc.models.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateCategoryRequestBody {
    private String name;
}
