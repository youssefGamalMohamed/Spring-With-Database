package com.youssef.ecommerce.app.jdbc.repositories.sqls.jooq.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.util.stream.Collectors;
import static com.youssef.ecommerce.app.jooq.ecommerce.Tables.PRODUCTS;
import static com.youssef.ecommerce.app.jooq.ecommerce.Tables.PRODUCTS_CATEGORIES;

@Repository
@AllArgsConstructor
@Slf4j
public class JooqProductRepoImpl implements ProductRepoInterface {

    private final DSLContext dslContext;
    private final DSLContext dsl;

    @Override
    public boolean isExistProductByNameIgnoreCase(String productName) {
        return dslContext.fetchExists(
                dslContext.selectFrom(PRODUCTS)
                        .where(PRODUCTS.NAME.equalIgnoreCase(productName))
                        .limit(1)
        );
    }
    @Override
    public Product save(Product product) {
        Integer productId = dslContext.insertInto(PRODUCTS)
                .set(PRODUCTS.NAME, product.getName())
                .set(PRODUCTS.QUANTITY, product.getQuantity())
                .set(PRODUCTS.PRICE, product.getPrice())
                .returningResult(PRODUCTS.PRODUCT_ID)
                .fetchOne()
                .getValue(PRODUCTS.PRODUCT_ID);

        product.setId(productId);

        // Insert relations into PRODUCTS_CATEGORIES table
        // Execute batch insert
        dslContext.batch(
                product.getCategories()
                        .stream()
                        .map(
                                category -> dslContext.insertInto(PRODUCTS_CATEGORIES)
                                        .set(PRODUCTS_CATEGORIES.PRODUCT_ID, productId)
                                        .set(PRODUCTS_CATEGORIES.CATEGORY_ID, category.getId())
                        )
                        .collect(Collectors.toList())
        ).execute();

        return product;
    }

    @Override
    public Product findById(Integer productId) {
        return dslContext.selectFrom(PRODUCTS)
                .where(PRODUCTS.PRODUCT_ID.equal(productId))
                        .fetchOne(
                                record -> Product.builder()
                                    .id(record.getProductId())
                                    .price(record.getPrice())
                                    .quantity(record.getQuantity())
                                    .name(record.getName())
                                    .build()
                        );

    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
        return null;
    }

    @Override
    public boolean deleteById(Integer productId) {
        int deletedCountFromProductsCategories = dslContext.deleteFrom(PRODUCTS_CATEGORIES)
                .where(PRODUCTS_CATEGORIES.PRODUCT_ID.equal(productId))
                .execute();
        int deletedCountFromProduct = dslContext.deleteFrom(PRODUCTS)
                .where(PRODUCTS.PRODUCT_ID.equal(productId))
                .execute();
        return (deletedCountFromProduct == 1 && deletedCountFromProductsCategories >= 0);
    }

    @Override
    public boolean updateProductById(Integer productId, Product product) {
        int updatedCount = dslContext.update(PRODUCTS)
                    .set(PRODUCTS.NAME, product.getName())
                    .set(PRODUCTS.QUANTITY, product.getQuantity())
                    .set(PRODUCTS.PRICE, product.getPrice())
                .where(PRODUCTS.PRODUCT_ID.equal(productId))
                .execute();
        return (updatedCount == 1);
    }
}
