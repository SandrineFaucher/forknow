package fr.maif.forknow.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.ResourceAccessException;


import fr.maif.forknow.dto.ShopDto;
import fr.maif.forknow.model.Shop;
import fr.maif.forknow.repositories.ShopRepository;
import fr.maif.forknow.service.ShopService;
import jakarta.validation.Valid;




@Controller
@RequestMapping
public class ShopController {

    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopService shopService;
  
 
    @GetMapping("/restaurant-list")
    public String showRestaurantList(Model model) {
        List<Shop> shops = shopRepository.findAll();
        // Vérifie si la liste des magasins est vide
        if (shops != null && !shops.isEmpty()) {
            // Ajoute l'objet "shops" au modèle
            model.addAttribute("shops", shops);
        }
        return "restaurant-list"; // retourne la vue de restaurant-list.html
    }

  
    @GetMapping("/restaurant")
    public String showForm(Model model){
        ShopDto shop = new ShopDto();
        model.addAttribute("shop", shop);
        return "restaurant"; // retourne vers la page de formulaire restaurant.html
    }

    
    @GetMapping("/restaurants")
    public List<Shop> getAllShop() {
        return this.shopRepository.findAll();
    }

    @GetMapping("/restaurant/{id}")
    public Shop getRestaurantById(@PathVariable("id") Long id) throws NotFoundException {
        return this.shopRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping("/restaurant")
    public String  createNewShop(@Valid @ModelAttribute ShopDto shopMapping){
        shopService.save(shopMapping);
        return "/restaurant-list";
        }

        

    // @PutMapping("/restaurant/{id}")
    // public Shop updateShop(@PathVariable("id") Long id, @RequestBody Shop updatedShop) {
    //     // Utilisation de l'Optional pour trouver le restaurant
    //     Optional<Shop> optionalShop = shopRepository.findById(id);

    //     // Vérification si l'utilisateur existe
    //     if (optionalShop.isPresent()) {
    //         Shop shop = optionalShop.get();

    //         // Mise à jour des propriétés du restaurant
    //         shop.setName(updatedShop.getName());
    //         shop.setType(updatedShop.getType());
    //         shop.setCreationDate(updatedShop.getCreationDate());

    //         // Sauvegarde du restaurant mis à jour
    //         return shopRepository.save(shop);
    //     } else {
    //         // Gestion du cas où le restaurant n'est pas trouvé
    //         throw new ResourceAccessException("Shop not found with id " + id);
    //     }
    // }



}