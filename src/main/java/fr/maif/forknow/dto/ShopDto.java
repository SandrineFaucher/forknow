package fr.maif.forknow.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDto {
    
    @NotEmpty(message = "Name must not be empty")
    private String name;
    
    @NotEmpty(message = "Type of shop must not be empty")
    private String type;

    private String imageUrl;

   
}


