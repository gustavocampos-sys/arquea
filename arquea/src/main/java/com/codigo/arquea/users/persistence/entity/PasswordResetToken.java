package com.codigo.arquea.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    //Recomendable usar Lazy para las relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    //findAllByUserId(Long UserId)
    private User user;

    private String token;
    private LocalDateTime expirationDate;
    private boolean isUsed;

    public PasswordResetToken() { }

    public PasswordResetToken(long id, User user, String token, LocalDateTime expirationDate, boolean isUsed) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
        this.isUsed = isUsed;
    }
}
