package com.andsemedodev.productservice;

import com.andsemedodev.productservice.dto.ProductRequest;
import com.andsemedodev.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers //to run this test
@AutoConfigureMockMvc //for MockMvc
class ProductServiceApplicationTests {

	@Container //JUnit will understand that this is a mongodb container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper; //to convert object to JSON and vice-versa
	@Autowired
	ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		//we need to pass the uri dynamically cause we're not using the monogb locally
		//we are using docker image
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	//Integration Test
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest request = getProductRequest();
		//convert request to string
		String requestString = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestString) //receive a string

		).andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 15")
				.description("Iphone 15")
				.price(BigDecimal.valueOf(145000))
				.build();
	}

}
