package com.Mohamed.billing_service.service;

import com.Mohamed.billing_service.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8888/customers")
public interface CustomerServiceClient {
    @GetMapping("/{id}")
    Customer findCustomerById(@PathVariable("id") Long id);

}
