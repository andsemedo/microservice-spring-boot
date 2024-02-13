package com.andsemedodev.orderservice.controller;

import com.andsemedodev.orderservice.dto.OrderRequest;
import com.andsemedodev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeholder(orderRequest);
        return "Order Placed Successfully";
    }
}
