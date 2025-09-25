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

    //private Integer bussinesId; // Id del negocio al que pertenece la session

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_session_id")
    private List<Account> accounts = new ArrayList<>(); // Cuentas de la sesion

    //private Integer openBy; // Id del usuario que realizo la apertura
    //private Integer closeBy; // Id del usuario que realizo el cierre
    private boolean autoOpen; // Define si se abre con valores iniciales predeterminados

    public DailySession() {}

    public DailySession(Long id, String sessionDate, LocalDateTime openedAt, LocalDateTime closedAt, String status, List<Account> accounts, boolean autoOpen) {
        this.id = id;
        this.sessionDate = sessionDate;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.status = status;
        this.accounts = accounts;
        this.autoOpen = autoOpen;
    }
}
