package com.emazon.shopping_cart.infraestructure.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tester_class")
public class TesterClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private boolean exito;

    public TesterClassEntity(String uuid) {
        this.uuid = uuid;
    }

    public TesterClassEntity() {
    }
}