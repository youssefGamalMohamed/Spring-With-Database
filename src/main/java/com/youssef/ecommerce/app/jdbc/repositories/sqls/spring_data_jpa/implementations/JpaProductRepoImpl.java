package com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.interfaces.JpaCategoryRepo;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.interfaces.JpaProductRepo;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaCategory;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.models.JpaProduct;
import com.youssef.ecommerce.app.jdbc.repositories.sqls.spring_data_jpa.repo_to_service_mapper.JpaProductServiceMapper;
import com.youssef.ecommerce.app.jdbc.services.models.Category;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class JpaProductRepoImpl implements ProductRepoInterface {

    @Lazy
    @Autowired
    private JpaProductRepo jpaProductRepo;

    @Autowired
    private JpaCategoryRepo jpaCategoryRepo;

    @Override
    public boolean isExistProductByNameIgnoreCase(String productName) {
        return jpaProductRepo.existsByNameIgnoreCase(productName);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        JpaProduct entity = JpaProductServiceMapper.toEntity(product);

        // Clear the existing categories from the entity
        entity.getCategories().clear();

        // Save the product to get the ID
        entity = jpaProductRepo.save(entity);

        // Retrieve managed categories from the database
        Set<JpaCategory> managedCategories = product.getCategories()
                .stream()
                .map(Category::getId)
                .map(id -> jpaCategoryRepo.findById(id))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        // Update bidirectional association
        for (JpaCategory jpaCategory : managedCategories) {
            jpaCategory.getJpaProducts().add(entity);
        }

        // Set the managed categories to the product
        entity.setCategories(managedCategories);

        // Save the product and the associated categories
        entity = jpaProductRepo.save(entity);

        return JpaProductServiceMapper.toServiceModel(entity);
    }

    @Override
    public Product findById(Integer productId) {
        Optional<JpaProduct> jpaProduct = jpaProductRepo.findById(productId);
        if(jpaProduct.isEmpty())
            return null;
        return JpaProductServiceMapper.toServiceModel(jpaProduct.get());
    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
        Optional<JpaProduct> jpaProduct = jpaProductRepo.findById(productId);
        if(jpaProduct.isEmpty())
            return Product.builder().build();
        return JpaProductServiceMapper.toServiceModel(jpaProduct.get());
    }

    @Override
    public boolean deleteById(Integer productId) {
        Optional<JpaProduct> optionalJpaProduct = jpaProductRepo.findById(productId);
        if (optionalJpaProduct.isEmpty()) {
            return false;
        }

        JpaProduct jpaProduct = optionalJpaProduct.get();

        // Remove the product from associated categories
        for (JpaCategory jpaCategory : jpaProduct.getCategories()) {
            jpaCategory.getJpaProducts().remove(jpaProduct);
        }
        jpaProduct.setCategories(new HashSet<>());

        // Save the product after removing associations
        jpaProductRepo.save(jpaProduct);

        // Delete the product
        jpaProductRepo.deleteById(productId);

        return true;
    }

    @Override
    public boolean updateProductById(Integer productId, Product product) {
        Optional<JpaProduct> entity = jpaProductRepo.findById(productId);
        if(entity.isEmpty())
            return false;
        entity.get().setName(product.getName());
        entity.get().setQuantity(product.getQuantity());
        entity.get().setPrice(product.getPrice());
        jpaProductRepo.save(entity.get());
        return true;
    }
}
