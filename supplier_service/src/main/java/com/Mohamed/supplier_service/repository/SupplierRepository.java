package com.Mohamed.supplier_service.repository;

import com.Mohamed.supplier_service.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
