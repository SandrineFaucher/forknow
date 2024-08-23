package fr.maif.forknow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import fr.maif.forknow.dto.ShopDto;
import fr.maif.forknow.model.Shop;
import fr.maif.forknow.model.User;
import fr.maif.forknow.repositories.ShopRepository;
import fr.maif.forknow.service.ShopService;
import fr.maif.forknow.service.UserService;
import jakarta.validation.Valid;




@Controller
@RequestMapping
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopService shopService;
    @Autowired
    private  UserService userService;
  
    // retourne l'objet créé en base de données dans la vue de restaurant-list.html
    @GetMapping("/restaurant-list")
    public String showRestaurantList(Model model, Authentication authentication) {
        List<Shop> shops = shopRepository.findAll();

        // Vérifie si la liste des magasins est vide
        if (shops != null && !shops.isEmpty()) {
            // Ajoute l'objet "shops" au modèle
            model.addAttribute("shops", shops);
        }
        for (Shop shop : shops) {
            // Utilisez le service pour générer l'URL de l'image
            shop.setImageUrl(shopService.generateImageUrl(shop.getId()));
        }
        Optional <User> user = userService.from(authentication);
        user.ifPresent(currentUser -> model.addAttribute("currentUser", currentUser));
        return "restaurant-list"; 
    }

    // retourne vers la page de formulaire restaurant.html
    @GetMapping("/restaurant")
    public String showForm(Model model){
        ShopDto shop = new ShopDto();
        model.addAttribute("shop", shop);
        return "restaurant"; 
    }

    
    // sauvegarde un shop en base de données et redirige vers la page restauran-list
    @PostMapping("/restaurant")
    public String  createNewShop(@Valid @ModelAttribute ShopDto shopMapping, Authentication authentication){
        Optional<User> user = userService.from(authentication);
        if(user.isPresent()){
            shopService.save(shopMapping, user.get());
        }
        return "redirect:/restaurant-list";
        }
    
        
    @PostMapping("/restaurant/{shopId}")
    public String deleteShop(@PathVariable("shopId") Long shopId, Authentication authentication) {
        
        shopService.deleteShop(shopId, authentication);
        return "redirect:/restaurant-list";
       
    }

    }
