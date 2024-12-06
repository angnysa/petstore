package org.angnysa.petstore.backend.pet.rest;

import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.pet.service.PetService;
import org.angnysa.petstore.backend.rest.generated.api.PetApi;
import org.angnysa.petstore.backend.rest.generated.model.ModelApiResponse;
import org.angnysa.petstore.backend.rest.generated.model.Pet;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PetRestController implements PetApi {

    private final PetMapper mapper;
    private final PetService service;

    @Override
    public ResponseEntity<Pet> addPet(Pet pet) {
        pet = mapper.mapToRest(service.addPet(mapper.mapToEntity(pet)));
        UriComponents uri = MvcUriComponentsBuilder
                .fromMethodCall(on(PetRestController.class).getPetById(pet.getId()))
                .buildAndExpand(1);
        return ResponseEntity.created(uri.toUri()).body(pet);
    }

    @Override
    public ResponseEntity<Void> deletePet(Long petId, String apiKey) {
        if (service.deletePet(petId.intValue())) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByStatus(String status) {
        return ResponseEntity.ok(service.findPetsByStatus(status).stream().map(mapper::mapToRest).toList());
    }

    @Override
    public ResponseEntity<List<Pet>> findPetsByTags(List<String> tags) {
        return ResponseEntity.ok(service.findPetsByTags(tags).stream().map(mapper::mapToRest).toList());
    }

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return ResponseEntity.of(service.findPetById(petId.intValue()).map(mapper::mapToRest));
    }

    @Override
    public ResponseEntity<Pet> updatePet(Pet pet) {
        return ResponseEntity.of(service.updatePet(mapper.mapToEntity(pet)).map(mapper::mapToRest));
    }

    @Override
    public ResponseEntity<Void> updatePetWithForm(Long petId, String name, String status) {
        service.updatePet(petId.intValue(), name, status).map(mapper::mapToRest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ModelApiResponse> uploadFile(Long petId, String additionalMetadata, Resource body) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
