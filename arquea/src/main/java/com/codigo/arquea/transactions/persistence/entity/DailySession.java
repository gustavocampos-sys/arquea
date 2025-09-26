package com.codigo.arquea.transactions.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "daily_sessions")
@Getter
@Setter
public class DailySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sessionDate; // Es la fecha a la que pertenece la session
    private LocalDateTime openedAt; // Fecha de apertura de la session
    private LocalDateTime closedAt; // Fecha de cierre de la session
    private String status; // Estado de la session (Abierto o Cerrado)
    private boolean autoOpen; // Define si se abre con valores iniciales predeterminados

    // Establece la relacion de muchos a uno con el negocio (Muchas sesiones diarias pueden pertenecer a un mismo negocio)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id",nullable = false)
    private Business business;

    public DailySession() {}

    public DailySession(Long id, String sessionDate, LocalDateTime openedAt, LocalDateTime closedAt, String status, boolean autoOpen, Business business) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.status = status;
        this.autoOpen = autoOpen;
        this.business = business;
    }
}
