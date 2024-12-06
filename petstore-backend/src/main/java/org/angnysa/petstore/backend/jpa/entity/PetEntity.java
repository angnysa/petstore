package org.angnysa.petstore.backend.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pet")
@Getter
@Setter
public class PetEntity {
    @Id private int id;
    private String name;

    @ManyToOne
    private CategoryEntity category;
    private String status;

    @ManyToMany
    @JoinTable(name = "pet_tag",
            joinColumns = @JoinColumn(name = "pet_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<TagEntity> tags;
}
