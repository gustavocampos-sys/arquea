package com.codigo.arquea.transactions.persistence.entity;

import com.codigo.arquea.users.persistence.entity.User;
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

    // Establece la relacion de muchos a uno con los usuarios (Muchos negocios pueden tener el mismo usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    public Business() {}

    public Business(long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
    }
}
