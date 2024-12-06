package org.angnysa.petstore.backend.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "tag")
@Getter
@Setter
public class TagEntity {
    @Id private int id;
    private String name;
}
