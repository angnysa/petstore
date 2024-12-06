package org.angnysa.petstore.backend.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "order_")
@Getter
@Setter
public class OrderEntity {
    @Id private int id;
    @ManyToOne
    private PetEntity pet;
    private int quantity;
    private Instant shipDate;
    private String status;
    private boolean complete;
}
