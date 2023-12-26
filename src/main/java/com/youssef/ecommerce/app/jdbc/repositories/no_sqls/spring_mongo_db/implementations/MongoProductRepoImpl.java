package com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.implementations;

import com.youssef.ecommerce.app.jdbc.repositories.core_interfaces.ProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.generators.NextSequenceGeneratorService;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.interfaces.MongoProductRepoInterface;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.MongoProduct;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.models.sequences.CustomSequence;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.repo_to_service_mapper.MongoCategoryServiceMapper;
import com.youssef.ecommerce.app.jdbc.repositories.no_sqls.spring_mongo_db.repo_to_service_mapper.MongoProductServiceMapper;
import com.youssef.ecommerce.app.jdbc.services.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoProductRepoImpl implements ProductRepoInterface {

    @Autowired
    private MongoProductRepoInterface productRepo;

    @Autowired
    private NextSequenceGeneratorService nextSequenceGeneratorService;

    @Override
    public boolean isExistProductByNameIgnoreCase(String productName) {
        return false;
    }

    @Override
    public Product save(Product product) {

        MongoProduct entity = MongoProduct.builder()
                .id(String.valueOf(nextSequenceGeneratorService.getNextSequence(CustomSequence.CUSTOM_SEQUENCE_COLUMN_NAME)))
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .categories(MongoCategoryServiceMapper.toEntities(product.getCategories()))
                .build();

        entity = productRepo.save(entity);

        product.setId((int) productRepo.count());

        return MongoProductServiceMapper.toServiceModel(entity);
    }

    @Override
    public Product findById(Integer productId) {
        Optional<MongoProduct> entity = productRepo.findById(String.valueOf(productId));
        if(entity.isEmpty())
            return null;
        return MongoProductServiceMapper.toServiceModel(entity.get());
    }

    @Override
    public Product findByIdWithCategories(Integer productId) {
        Optional<MongoProduct> entity = productRepo.findById(String.valueOf(productId));
        if(entity.isEmpty())
            return null;
        return MongoProductServiceMapper.toServiceModelWithCategories(entity.get());
    }

    @Override
    public boolean deleteById(Integer productId) {
        productRepo.deleteById(String.valueOf(productId));
        return true;
    }

    @Override
    public boolean updateProductById(Integer productId, Product product) {
        Optional<MongoProduct> entity = productRepo.findById(String.valueOf(productId));
        if(entity.isEmpty())
            return false;
        entity.get().setName(product.getName());
        entity.get().setQuantity(product.getQuantity());
        entity.get().setPrice(product.getPrice());
        productRepo.save(entity.get());
        return true;
    }
}
