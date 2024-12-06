package org.angnysa.petstore.backend.store.rest;

import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.rest.generated.api.StoreApi;
import org.angnysa.petstore.backend.rest.generated.model.Order;
import org.angnysa.petstore.backend.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StoreRestController implements StoreApi {

    private final StoreMapper mapper;
    private final StoreService service;

    @Override
    public ResponseEntity<Void> deleteOrder(Long orderId) {
        if (service.deleteOrder(orderId.intValue())) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Map<String, Integer>> getInventory() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long orderId) {
        return ResponseEntity.of(service.findOrder(orderId.intValue()).map(mapper::mapToRest));
    }

    @Override
    public ResponseEntity<Order> placeOrder(Order order) {
        return ResponseEntity.ok(mapper.mapToRest(service.createOrder(mapper.mapToEntity(order))));
    }
}
