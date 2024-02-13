package com.andsemedodev.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(value = "product") //defines as mongodb document
//lombo anotation to define getter & setter and contructors
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductModel {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
