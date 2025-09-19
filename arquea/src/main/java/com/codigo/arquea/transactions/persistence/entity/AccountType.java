package com.codigo.arquea.transactions.persistence.entity;

import com.codigo.arquea.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "account_types")
@Getter
@Setter
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    public AccountType() {}

    public AccountType(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
