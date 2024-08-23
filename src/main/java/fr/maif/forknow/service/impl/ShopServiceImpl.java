package fr.maif.forknow.service.impl;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import fr.maif.forknow.dto.ShopDto;
import fr.maif.forknow.model.Shop;
import fr.maif.forknow.model.User;
import fr.maif.forknow.repositories.ShopRepository;
import fr.maif.forknow.service.ShopService;
import fr.maif.forknow.service.UserService;



@Service
public class ShopServiceImpl implements ShopService {
@Autowired
private ShopRepository shopRepository;
@Autowired
private UserService userService;

    @Override
    public void save(ShopDto shop, User user) {
        Shop shopEntity = Shop.builder()
            .user(user)
            .name(shop.getName())
            .type(shop.getType())
            .imageUrl(shop.getImageUrl())
            .build();
        shopRepository.save(shopEntity);
    }


    @Override
    public void deleteShop(Long shopId, Authentication authentication) {
        // Récupérer l'utilisateur actuel
        Optional<User> currentUser = userService.from(authentication);

        // Récupérer le shop par son ID
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new ResourceAccessException("Shop not found with id " + shopId));

        // Vérifier si l'utilisateur actuel est le propriétaire du shop
        if (currentUser.isPresent() && shop.getUser().getId().equals(currentUser.get().getId())) {
            // Supprimer le shop si l'utilisateur est le propriétaire
            shopRepository.delete(shop);
        } else {
            // Lancer une exception si l'utilisateur n'est pas autorisé à supprimer le shop
            throw new AccessDeniedException("You are not authorized to delete this shop.");
        }
    }

    public String generateImageUrl(Long id) {
        return "https://via.placeholder.com/150x100?text=Shop+"  + id;
    }

}
