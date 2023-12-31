package com.Mohamed.supplier_service.web;

import com.Mohamed.supplier_service.entity.Supplier;
import com.Mohamed.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/all")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    @GetMapping
    public List<Supplier> getsupliers() {
        return supplierRepository.findAll();
    }
}
