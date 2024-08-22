package fr.maif.forknow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.maif.forknow.dto.ShopDto;
import fr.maif.forknow.model.Shop;
import fr.maif.forknow.repositories.ShopRepository;
import fr.maif.forknow.service.ShopService;



@Service
public class ShopServiceImpl implements ShopService {
@Autowired
private ShopRepository shopRepository;

    @Override
    public void save(ShopDto shop) {
        Shop shopEntity = Shop.builder()
            .name(shop.getName())
            .type(shop.getType())
            .build();
        shopRepository.save(shopEntity);
    }
}
