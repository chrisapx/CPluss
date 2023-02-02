package com.company.cpluss.inventory_service.service;

import com.company.cpluss.inventory_service.model.Product;
import com.company.cpluss.inventory_service.repository.ProductRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Secured({"ADMIN","SUPERADMIN"})
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(String productName){
        productRepository.deleteByProductName(productName);
    }


}
