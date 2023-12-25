package com.youssef.ecommerce.app.jdbc.services.interfaces;

import com.youssef.ecommerce.app.jdbc.controllers.models.requests.AddProductRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateProductRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.AddProductResponseBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindProductByIdResponseBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindProductByIdWithCategoriesResponseBody;
import com.youssef.ecommerce.app.jdbc.services.models.Product;

public interface ProductServiceInterface {

    Product addNewProduct(AddProductRequestBody addProductRequestBody);

    Product findById(Integer productId);

    Product findByIdWithCategories(Integer productId);

    boolean deleteById(Integer productId);

    boolean updateById(Integer productId, UpdateProductRequestBody updateProductRequestBody);
}
