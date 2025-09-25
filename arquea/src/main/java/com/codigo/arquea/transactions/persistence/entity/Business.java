package com.codigo.arquea.transactions.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "business")
@Getter
@Setter
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name; // Nombre del negocio o empresa
    private String description; // Descripcion del negocio opcional
    private LocalDateTime createdAt; // Fecha de creacion
    private LocalDateTime updatedAt; // Fecha de actualizacion

    // Relacion de uno a muchos de negocio a sesiones (un negocio puede tener muchas sesiones)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id")
    private Set<DailySession> sessions = new HashSet<>(); // Lista de sesiones

    public Business() {}

    public Business(long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, Set<DailySession> sessions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = sessions;
    }
}
