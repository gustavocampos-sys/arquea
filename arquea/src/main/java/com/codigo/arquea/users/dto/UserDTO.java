package com.codigo.arquea.users.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String profileImageUrl;
    private boolean isActive;
    private boolean isVerified;
}
