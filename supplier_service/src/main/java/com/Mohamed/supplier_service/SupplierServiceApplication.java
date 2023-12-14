package com.Mohamed.supplier_service;

import com.Mohamed.supplier_service.entity.Supplier;
import com.Mohamed.supplier_service.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SupplierServiceApplication implements CommandLineRunner {
	private final SupplierRepository supplierRepository;


	public static void main(String[] args) {
		SpringApplication.run(SupplierServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		supplierRepository.save(new Supplier(1L,"JBOSS","jboss.com"));
		supplierRepository.save(new Supplier(2L,"Tomcat","tomcat.com"));
		supplierRepository.save(new Supplier(3L,"Springboot","springboot.com"));

	}

}
