package fr.maif.forknow.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {

    @NotEmpty(message = "username must not be empty")
    private String username;

    @NotEmpty(message = "displayName must not be empty")
    private String displayName;

    @NotEmpty(message = "email must not be empty")
    private String email;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @NotEmpty(message = "confirm password must not be empty")
    private String passwordConfirm;
    
}
