package com.youssef.ecommerce.app.jdbc.controllers.implementations;

import com.youssef.ecommerce.app.jdbc.controllers.mappers.responses.AddProductResponseBodyMapper;
import com.youssef.ecommerce.app.jdbc.controllers.mappers.responses.FindProductByIdResponseMapper;
import com.youssef.ecommerce.app.jdbc.controllers.mappers.responses.FindProductByIdWithCategoriesResponseMapper;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.AddProductRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateProductRequestBody;
import com.youssef.ecommerce.app.jdbc.services.implementations.ProductServiceImpl;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequestBody addProductRequestBody) {

        Product product = productService.addNewProduct(addProductRequestBody);

        if(product == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return ResponseEntity.ok(
                AddProductResponseBodyMapper.toControllerModel(product)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer productId) {
        Product product = productService.findById(productId);
        if(product.getId() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(
                FindProductByIdResponseMapper.toControllerModel(product)
        );
    }


    @GetMapping("/{id}/categories")
    public ResponseEntity<?> findByIdWithCategories(@PathVariable("id") Integer productId) {
        Product product = productService.findByIdWithCategories(productId);
        if(product.getId() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(
                FindProductByIdWithCategoriesResponseMapper.toControllerModel(product)
        );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer productId) {
        boolean isDeleted = productService.deleteById(productId);

        if(!isDeleted)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer productId , @RequestBody UpdateProductRequestBody updateProductRequestBody) {

        boolean isUpdated = productService.updateById(productId , updateProductRequestBody);
        if(!isUpdated)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
