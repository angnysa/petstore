package org.angnysa.petstore.backend.store.service;

import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.jpa.entity.OrderEntity;
import org.angnysa.petstore.backend.jpa.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final OrderRepository orderRepository;

    public boolean deleteOrder(int orderId) {
        Optional<OrderEntity> pet = orderRepository.findById(orderId);
        if (pet.isPresent()) {
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    public Optional<OrderEntity> findOrder(int orderId) {
        return orderRepository.findById((int) orderId);
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }
}
