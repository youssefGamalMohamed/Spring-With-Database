package com.youssef.ecommerce.app.jdbc.controllers;

import com.youssef.ecommerce.app.jdbc.models.requests.AddProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.requests.UpdateProductRequestBody;
import com.youssef.ecommerce.app.jdbc.models.responses.AddProductResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindProductByIdWithCategoriesResponseBody;
import com.youssef.ecommerce.app.jdbc.services.implementations.ProductServiceImpl;
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

        AddProductResponseBody addProductResponseBody = productService.addNewProduct(addProductRequestBody);

        if(addProductResponseBody.getId() == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return ResponseEntity.ok(addProductResponseBody);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer productId) {
        FindProductByIdResponseBody responseBody = productService.findById(productId);
        if(responseBody.getId() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(responseBody);
    }


    @GetMapping("/{id}/categories")
    public ResponseEntity<?> findByIdWithCategories(@PathVariable("id") Integer productId) {
        FindProductByIdWithCategoriesResponseBody responseBody = productService.findByIdWithCategories(productId);
        if(responseBody.getId() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(responseBody);
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
