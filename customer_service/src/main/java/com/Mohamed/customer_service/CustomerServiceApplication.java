package com.Mohamed.customer_service;

import com.Mohamed.customer_service.entity.Customer;
import com.Mohamed.customer_service.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;


@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	public RepositoryRestConfigurer repositoryRestConfigurer() {
		return RepositoryRestConfigurer.withConfig(config -> {
			config.exposeIdsFor(Customer.class);
		});
	}


	@Bean
	CommandLineRunner start(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer(null,"ENI","contact@eni.tn"));
			customerRepository.save(new Customer(null,"FST","contact@fst.tn"));
			customerRepository.save(new Customer(null,"ENSI","contact@ensi.tn"));
			customerRepository.findAll().forEach(System.out::println);
		};
	}

}
