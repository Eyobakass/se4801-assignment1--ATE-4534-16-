package com.shopwave.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Order order;
}