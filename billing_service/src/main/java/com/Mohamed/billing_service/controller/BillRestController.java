package com.Mohamed.billing_service.controller;

import com.Mohamed.billing_service.billservice.BillService;
import com.Mohamed.billing_service.entity.Bill;
import com.Mohamed.billing_service.repository.BillRepository;
import com.Mohamed.billing_service.repository.ProductItemRepository;
import com.Mohamed.billing_service.service.CustomerServiceClient;
import com.Mohamed.billing_service.service.InventoryServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillRestController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerServiceClient customerServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final BillService billService;



    @GetMapping("/bills/full/{id}")
    Bill getBill(@PathVariable("id") Long id){
        billService.initBill();
        Bill bill = billRepository.findById(id).get();
        bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerId()));
        bill.setProductItems(productItemRepository.findByBillId(id));
        bill.getProductItems().forEach(productItem -> {
            productItem.setProduct(inventoryServiceClient.findProductById(productItem.getId()));
        });
        return bill;}


}
