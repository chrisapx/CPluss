package com.company.cpluss.inventory_service.controller;

import com.company.cpluss.inventory_service.model.Product;
import com.company.cpluss.inventory_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }


}
