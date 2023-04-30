package com.example.cdicontextos.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private Integer id;
    private String productName;
    private Double price;
    private LocalDate dateRegister;
    private Category category;

    public Product(String productName, Double price, LocalDate dateRegister, Category category) {
        this.productName = productName;
        this.price = price;
        this.dateRegister = dateRegister;
        this.category = category;
    }

}
