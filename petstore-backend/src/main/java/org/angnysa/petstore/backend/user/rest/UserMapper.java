package org.angnysa.petstore.backend.user.rest;

import org.angnysa.petstore.backend.jpa.entity.UserEntity;
import org.angnysa.petstore.backend.rest.generated.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    @Mapping(target = "status", source = "userStatus")
    UserEntity mapToEntity(User user);

    @Mapping(target = "userStatus", source = "status")
    User mapToRest(UserEntity user);
}
