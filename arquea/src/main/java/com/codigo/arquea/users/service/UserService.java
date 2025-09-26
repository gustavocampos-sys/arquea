package com.codigo.arquea.users.service;

import com.codigo.arquea.exception.NotFoundException;
import com.codigo.arquea.users.dto.RegistrationRequest;
import com.codigo.arquea.users.dto.UserDTO;
import com.codigo.arquea.users.dto.UserRegistrationResponse;
import com.codigo.arquea.users.mapper.UserMapper;
import com.codigo.arquea.users.persistence.entity.User;
import com.codigo.arquea.users.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j // Logging
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Integer id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::mapToDTO)// La sesion se pierde
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    public Page<User> findAll(int page, int size) {
        log.info("Finding all users - Page: {}, Size: {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.info("Finding all users");
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapToDTO)
                .toList();
    }

    public void register(@Valid RegistrationRequest registrationRequest) {
        log.info("Registering user with email: {}", registrationRequest.getEmail());

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + registrationRequest.getEmail());
        }

        final User user = new User();
        user.setEmail(registrationRequest.getEmail());
        String username = registrationRequest.getFirstName().toLowerCase() + "." +
                registrationRequest.getLastName().toLowerCase(); // Andre Gallegos andre.gallegos
        user.setUsername(username);
        user.setPassword(registrationRequest.getPassword());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setProfileImageUrl(registrationRequest.getProfileImageUrl());
        user.setActive(true);
        user.setVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Guardar usuario
        userRepository.save(user);
    }

    public void updateUser(Integer id, @Valid RegistrationRequest updateRequest) {
        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        user.setEmail(updateRequest.getEmail());
        user.setPassword(updateRequest.getPassword());
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        user.setProfileImageUrl(updateRequest.getProfileImageUrl());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        log.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        User userToDelete = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
        userRepository.delete(userToDelete);
    }
}