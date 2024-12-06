package org.angnysa.petstore.backend.pet.rest;

import org.angnysa.petstore.backend.jpa.entity.PetEntity;
import org.angnysa.petstore.backend.rest.generated.model.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PetMapper {
    PetEntity mapToEntity(Pet pet);

    @Mapping(target = "photoUrls", ignore = true)
    Pet mapToRest(PetEntity pet);
}
