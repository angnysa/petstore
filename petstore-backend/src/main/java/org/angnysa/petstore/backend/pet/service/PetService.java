package org.angnysa.petstore.backend.pet.service;

import lombok.RequiredArgsConstructor;
import org.angnysa.petstore.backend.jpa.entity.PetEntity;
import org.angnysa.petstore.backend.jpa.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetEntity addPet(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public boolean deletePet(int petId) {
        Optional<PetEntity> pet = petRepository.findById(petId);
        if (pet.isPresent()) {
            petRepository.delete(pet.get());
            return true;
        } else {
            return false;
        }
    }

    public List<PetEntity> findPetsByStatus(String status) {
        return petRepository.findByStatus(status);
    }

    public List<PetEntity> findPetsByTags(List<String> tags) {
        return petRepository.findByTags(tags);
    }

    public Optional<PetEntity> findPetById(int petId) {
        return petRepository.findById(petId);
    }

    public Optional<PetEntity> updatePet(PetEntity changes) {
        return updatePet(changes.getId(), changes.getName(), changes.getStatus());
    }

    public Optional<PetEntity> updatePet(int petId, String name, String status) {
        Optional<PetEntity> petOpt = petRepository.findById(petId);
        if (petOpt.isPresent()) {
            PetEntity pet = petOpt.get();
            pet.setName(name);
            pet.setStatus(status);
            return Optional.of(petRepository.save(pet));
        } else {
            return Optional.empty();
        }

    }
}
