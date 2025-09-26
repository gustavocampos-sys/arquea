package com.codigo.arquea.users.mapper;

import com.codigo.arquea.users.dto.UserDTO;
import com.codigo.arquea.users.persistence.entity.User;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO mapToDTO(User user) {
        if (user == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setProfileImageUrl(user.getProfileImageUrl());
        userDTO.setActive(user.isActive());
        userDTO.setVerified(user.isVerified());

        return userDTO;
    }
}