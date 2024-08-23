package fr.maif.forknow.service;

import org.springframework.security.core.Authentication;

import fr.maif.forknow.dto.ShopDto;
import fr.maif.forknow.model.User;


public interface ShopService {
    void save(ShopDto shop, User user);

    void deleteShop(Long id, Authentication authentication);

    String generateImageUrl(Long id);
}
