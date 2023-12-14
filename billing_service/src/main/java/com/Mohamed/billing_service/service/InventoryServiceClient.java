package com.Mohamed.billing_service.service;

import com.Mohamed.billing_service.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service", url = "http://localhost:8888/products")
public interface InventoryServiceClient {

    @GetMapping("/{id}")
    Product findProductById(@PathVariable("id") Long id);
    @GetMapping
    PagedModel<Product> findAll();
}

