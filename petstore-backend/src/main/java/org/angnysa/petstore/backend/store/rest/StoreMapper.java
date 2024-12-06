package org.angnysa.petstore.backend.store.rest;

import org.angnysa.petstore.backend.jpa.entity.OrderEntity;
import org.angnysa.petstore.backend.rest.generated.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StoreMapper {
    @Mapping(target = "pet.id", source = "order.petId")
    OrderEntity mapToEntity(Order order);

    @Mapping(target = "petId", source = "pet.id")
    Order mapToRest(OrderEntity order);

    static Instant map(OffsetDateTime odt) {
        return odt.toInstant();
    }

    static OffsetDateTime map(Instant instant) {
        return instant.atOffset(ZoneOffset.UTC);
    }
}
