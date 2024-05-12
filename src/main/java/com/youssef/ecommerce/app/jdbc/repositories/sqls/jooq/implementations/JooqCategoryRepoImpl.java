package com.youssef.ecommerce.app.jdbc.repositories.sqls.jooq.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.CategoryRepoInterface;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jooq.ecommerce.tables.pojos.Categories;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.youssef.ecommerce.app.jooq.ecommerce.tables.Categories.CATEGORIES;
import static com.youssef.ecommerce.app.jooq.ecommerce.tables.ProductsCategories.PRODUCTS_CATEGORIES;

@Repository
@AllArgsConstructor
@Slf4j
public class JooqCategoryRepoImpl implements CategoryRepoInterface {

    private final DSLContext dslContext;

    @Override
    public boolean isExistCategoryByNameIgnoreCase(String categoryName) {
        int numberOfExistingCategories = dslContext.selectFrom(CATEGORIES)
                .where(CATEGORIES.NAME.eq(categoryName))
                .fetch()
                .size();

        log.info("Number of existing categories: {}", numberOfExistingCategories);
        return numberOfExistingCategories > 0;
    }

    @Override
    public Category saveCategory(Category category) {
        // Insert a new category into the database and fetch the generated ID
        Integer categoryId = dslContext.insertInto(CATEGORIES, CATEGORIES.NAME)
                .values(category.getName())
                .returningResult(CATEGORIES.CATEGORY_ID)
                .fetchOne()
                .getValue(CATEGORIES.CATEGORY_ID);
        if (categoryId == null) {
            throw new IllegalArgumentException("Failed to add new category to the database: " + category);
        }
        log.info("Category saved successfully with ID: {}", categoryId);
        category.setId(categoryId);
        return category;
    }

    @Override
    public boolean existsCategoryById(Integer categoryId) {
        return dslContext.fetchExists(
                dslContext.selectFrom(CATEGORIES)
                        .where(CATEGORIES.CATEGORY_ID.eq(categoryId))
                        .limit(1)
        );
    }

    @Override
    public Optional<Category> findCategoryById(Integer categoryId) {
        Categories category = dslContext
                .selectFrom(CATEGORIES)
                .where(CATEGORIES.CATEGORY_ID.eq(categoryId))
                .fetchOneInto(Categories.class);

        if (category == null) {
            throw new NoSuchElementException("Category not found with ID: " + categoryId);
        }

        Category categoryModel = Category.builder()
                .id(category.getCategoryId())
                .name(category.getName())
                .build();

        log.info("Category found: {}", categoryModel);
        return Optional.of(categoryModel);
    }

    @Override
    public boolean deleteCategoryById(Integer categoryId) {
        int deleteRowsInProductsCategories = dslContext.deleteFrom(PRODUCTS_CATEGORIES)
                .where(PRODUCTS_CATEGORIES.CATEGORY_ID.eq(categoryId))
                .execute();

        int deletedRowsCount = dslContext.deleteFrom(CATEGORIES)
                .where(CATEGORIES.CATEGORY_ID.eq(categoryId))
                .execute();
        return (deletedRowsCount == 1 && deleteRowsInProductsCategories > 0);
    }

    @Override
    public boolean updateCategoryById(Integer categoryId, Category category) {
        int updatedRowsCount = dslContext.update(CATEGORIES)
                .set(CATEGORIES.NAME, category.getName())
                .where(CATEGORIES.CATEGORY_ID.eq(categoryId))
                .execute();
        return (updatedRowsCount == 1);
    }
}
