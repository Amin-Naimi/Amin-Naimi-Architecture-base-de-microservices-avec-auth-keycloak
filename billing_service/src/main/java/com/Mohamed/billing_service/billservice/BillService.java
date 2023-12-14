package com.Mohamed.billing_service.billservice;

import com.Mohamed.billing_service.entity.Bill;
import com.Mohamed.billing_service.entity.Customer;
import com.Mohamed.billing_service.entity.ProductItem;
import com.Mohamed.billing_service.repository.BillRepository;
import com.Mohamed.billing_service.repository.ProductItemRepository;
import com.Mohamed.billing_service.service.CustomerServiceClient;
import com.Mohamed.billing_service.service.InventoryServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final CustomerServiceClient customerServiceClient;


    public void initBill(){
        Bill bill = new Bill();
        bill.setBillingDate(new Date());
        Customer customer = customerServiceClient.findCustomerById(1L);
        System.out.println(customer.toString());
        bill.setCustomerId(customer.getId());
        billRepository.save(bill);
        inventoryServiceClient.findAll().getContent().forEach(product -> {
            productItemRepository.save(
                    new ProductItem(null, null, product.getId(), product.getPrice(),(int)(1+Math.random()*1000),bill)
            );
        });
        productItemRepository.findAll().forEach(System.out::println);

    }

}