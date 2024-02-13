package com.andsemedodev.inventoryservice;

import com.andsemedodev.inventoryservice.model.InventoryModel;
import com.andsemedodev.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			InventoryModel inventory = new InventoryModel();
			inventory.setSkuCode("redmi_12_s");
			inventory.setQuantity(10);

			InventoryModel inventory1 = new InventoryModel();
			inventory1.setSkuCode("samsung_a54");
			inventory1.setQuantity(13);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}
