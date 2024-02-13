package com.andsemedodev.productservice.service;

import com.andsemedodev.productservice.dto.ProductRequest;
import com.andsemedodev.productservice.dto.ProductResponse;
import com.andsemedodev.productservice.model.ProductModel;
import com.andsemedodev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //to use log
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        // build the ProductModel
        ProductModel product = ProductModel.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductModel> products = productRepository.findAll();

        return products.stream().map(this::mapToProductresponse).toList();
    }

    private ProductResponse mapToProductresponse(ProductModel productModel) {
        return ProductResponse.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice())
                .build();
    }
}
