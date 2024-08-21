package fr.maif.forknow.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;


import fr.maif.forknow.dto.RegisterDto;
import fr.maif.forknow.dto.UserDto;
import fr.maif.forknow.model.User;


public interface UserService {
    void saveUser(UserDto user);

    void saveUser(RegisterDto userMapping);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String Email);

    Optional<User> from(Authentication authentication);

}
