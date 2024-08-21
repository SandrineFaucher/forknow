package fr.maif.forknow.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
    @NotEmpty(message = "username must not be empty")
    private String username;
    
    @NotEmpty(message = "password must not be empty")
    private String password;
}
