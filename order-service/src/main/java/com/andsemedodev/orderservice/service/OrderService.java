package com.andsemedodev.orderservice.service;

import com.andsemedodev.orderservice.dto.OrderLineItemsDto;
import com.andsemedodev.orderservice.dto.OrderRequest;
import com.andsemedodev.orderservice.model.OrderLineItemsModel;
import com.andsemedodev.orderservice.model.OrderModel;
import com.andsemedodev.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeholder(OrderRequest orderRequest) {
        OrderModel order = new OrderModel();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItemsModel> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItemsList);

        orderRepository.save(order);
    }

    private OrderLineItemsModel mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItemsModel orderLineItems = new OrderLineItemsModel();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return  orderLineItems;
    }
}
