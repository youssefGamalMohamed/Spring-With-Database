package com.youssef.ecommerce.app.jdbc.controllers;


import com.youssef.ecommerce.app.jdbc.models.requests.AddCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.models.requests.UpdateCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.models.responses.AddCategoryResponseBody;
import com.youssef.ecommerce.app.jdbc.models.responses.FindCategoryByIdResponse;
import com.youssef.ecommerce.app.jdbc.services.implementations.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody AddCategoryRequestBody categoryRequestBody) {
        AddCategoryResponseBody addCategoryResponseBody = categoryService.addNewCategory(categoryRequestBody);
        if (addCategoryResponseBody.getId() == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(addCategoryResponseBody, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer categoryId) {
        FindCategoryByIdResponse response = categoryService.findById(categoryId);
        if (response.getId() == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer categoryId) {
        boolean isDeleted = categoryService.deleteById(categoryId);
        if (!isDeleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") Integer categoryId, @RequestBody UpdateCategoryRequestBody updateCategoryRequestBody) {


        boolean isUpdated = categoryService.updateCategoryById(categoryId, updateCategoryRequestBody);

        if (!isUpdated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
