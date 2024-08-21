package fr.maif.forknow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class RestaurantController {

    @GetMapping("/restaurant-list")
    public String showRestaurantList() {
        return "restaurant-list"; // retourne la vue de restaurant-list.html
    }

}
