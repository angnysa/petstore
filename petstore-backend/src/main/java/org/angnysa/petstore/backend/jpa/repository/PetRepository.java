package org.angnysa.petstore.backend.jpa.repository;

import org.angnysa.petstore.backend.jpa.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Integer> {
    List<PetEntity> findByStatus(String status);

    List<PetEntity> findByTags(List<String> tags);
}
