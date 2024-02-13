package com.andsemedodev.orderservice.repository;

import com.andsemedodev.orderservice.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
