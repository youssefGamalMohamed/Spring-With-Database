package com.youssef.ecommerce.app.jdbc.services.interfaces;

import com.youssef.ecommerce.app.jdbc.entities.Product;
import com.youssef.ecommerce.app.jdbc.models.requests.AddProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.requests.UpdateProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.responses.AddProductResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdWithCategoriesResponseBody;

public interface ProductServiceInterface {

    AddProductResponseBody addNewProduct(AddProductRequestBody addProductRequestBody);

    FindProductByIdResponseBody findById(Integer productId);

    FindProductByIdWithCategoriesResponseBody findByIdWithCategories(Integer productId);

    boolean deleteById(Integer productId);

    boolean updateById(Integer productId, UpdateProductRequestBody updateProductRequestBody);
}
