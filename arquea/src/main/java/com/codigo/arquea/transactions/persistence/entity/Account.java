package com.codigo.arquea.transactions.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name; // Nombre de la cuenta
    private String description; // Descripcion opcional
    private double initialBalance; // Monto Inicial de la cuenta por sesion
    private double finalBalance; // Monto final de la cuenta por sesion
    private String accountType; // Tipo de cuenta (Activo si suma o pasivo si resta)
    private boolean isActive; // Determina si la cuenta esta activa o no
    private LocalDateTime createdAt; // Fecha de creacion
    private LocalDateTime updatedAt; // Fecha de actualizacion

    // Establece la relacion de muchos a uno de cuentas a sesiones diarias (muchas cuentas pueden tener uns misma sesion)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_session_id",nullable = false)
    private DailySession dailySession;

    public Account() {}

    public Account(long id, String name, String description, double initialBalance, double finalBalance, String accountType, boolean isActive, LocalDateTime createdAt, LocalDateTime updatedAt, DailySession dailySession) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.accountType = accountType;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dailySession = dailySession;
    }
}

