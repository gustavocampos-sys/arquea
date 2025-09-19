package com.codigo.arquea.users.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_verification_tokens")
@Getter
@Setter
public class EmailVerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String token;
    private LocalDateTime expireDate;
    private boolean isRevoked;

    public EmailVerificationToken() { }

    public EmailVerificationToken(long id, User user, String token, LocalDateTime expireDate, boolean isRevoked) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.expireDate = expireDate;
        this.isRevoked = isRevoked;
    }
}
