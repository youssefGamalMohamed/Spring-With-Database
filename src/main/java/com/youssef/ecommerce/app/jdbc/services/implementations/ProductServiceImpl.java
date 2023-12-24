package com.youssef.ecommerce.app.jdbc.services.implementations;

import com.youssef.ecommerce.app.jdbc.entities.Category;
import com.youssef.ecommerce.app.jdbc.entities.Product;
import com.youssef.ecommerce.app.jdbc.mappers.responses.FindProductByIdResponseMapper;
import com.youssef.ecommerce.app.jdbc.mappers.responses.FindProductByIdWithCategoriesResponseMapper;
import com.youssef.ecommerce.app.jdbc.models.requests.AddProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.requests.UpdateProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.responses.AddProductResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdWithCategoriesResponseBody;
import com.youssef.ecommerce.app.jdbc.repositories.interfaces.ProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.services.interfaces.CategoryServiceInterface;
import com.youssef.ecommerce.app.jdbc.services.interfaces.ProductCategoryServiceInterface;
import com.youssef.ecommerce.app.jdbc.services.interfaces.ProductServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ProductServiceImpl implements ProductServiceInterface {


    // REPOS
    @Autowired
    @Qualifier("dataJdbcProductRepoImpl")
    private ProductRepoInterface productRepo;


    // SERVICES
    @Autowired
    private CategoryServiceInterface categoryService;

    @Autowired
    private ProductCategoryServiceInterface productCategoryService;

    @Override
    public AddProductResponseBody addNewProduct(AddProductRequestBody addProductRequestBody) {
        if (productRepo.isExistProductByNameIgnoreCase(addProductRequestBody.getName())) {
            log.error(">>>>> Product With Name = " + addProductRequestBody.getName() + " IS Already EXIST");
            return AddProductResponseBody.builder()
                    .id(null)
                    .build();
        }


        Set<Category> categories = new HashSet<>();
        // check existences of all Categories to assign it to the Product
        for (Integer categoryId : addProductRequestBody.getCategoriesIds()) {
            Optional<Category> category = categoryService.findCategoryById(categoryId);
            if (category.isEmpty()) {
                throw new NoSuchElementException(
                        "Can't Add Product , Reason : ( No Category with ID = " + categoryId + " to assign the " +
                        "this category for the Product )"
                );
            }
            else {
                categories.add(category.get());
            }
        }

        Product product = Product.builder()
                .name(addProductRequestBody.getName())
                .price(addProductRequestBody.getPrice())
                .quantity(addProductRequestBody.getQuantity())
                .categories(categories)
                .build();

        product = productRepo.save(product);


        return AddProductResponseBody.builder()
                .id(product.getId())
                .build();
    }

    @Override
    public FindProductByIdResponseBody findById(Integer productId) {
        Product product = productRepo.findById(productId);
        if(product == null)
            product = Product.builder().build();
        log.info(">>>>>> PRODUCT WITH ID = " + productId + " ==> " + product);
        return FindProductByIdResponseMapper.toResponse(product);
    }

    @Override
    public FindProductByIdWithCategoriesResponseBody findByIdWithCategories(Integer productId) {
        Product product = productRepo.findByIdWithCategories(productId);
        if(product == null)
            product = Product.builder().build();
        log.info(">>>>> PRODUCT WITH ID = " + productId + " ==> " + product);
        return FindProductByIdWithCategoriesResponseMapper.toResponse(product);
    }

    @Override
    public boolean deleteById(Integer productId) {
        return productCategoryService.deleteAllByProductId(productId) & productRepo.deleteById(productId);
    }

    @Override
    public boolean updateById(Integer productId, UpdateProductRequestBody updateProductRequestBody) {
        Product product = Product.builder()
                .name(updateProductRequestBody.getName())
                .price(updateProductRequestBody.getPrice())
                .quantity(updateProductRequestBody.getQuantity())
                .build();
        return productRepo.updateProductById(productId , product);
    }
}
