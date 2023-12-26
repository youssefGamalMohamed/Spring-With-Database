package com.youssef.ecommerce.app.jdbc.controllers.implementations;


import com.youssef.ecommerce.app.jdbc.controllers.mappers.responses.AddCategoryResponseMapper;
import com.youssef.ecommerce.app.jdbc.controllers.mappers.responses.FindCategoryByIdResponseMapper;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.AddCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.requests.UpdateCategoryRequestBody;
import com.youssef.ecommerce.app.jdbc.controllers.models.responses.FindCategoryByIdResponse;
import com.youssef.ecommerce.app.jdbc.services.implementations.CategoryServiceImpl;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
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
        Category category = categoryService.addNewCategory(categoryRequestBody);
        if (category == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(
                AddCategoryResponseMapper.toControllerModel(category),
                HttpStatus.CREATED
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(
                FindCategoryByIdResponseMapper.toControllerModel(category)
        );
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
