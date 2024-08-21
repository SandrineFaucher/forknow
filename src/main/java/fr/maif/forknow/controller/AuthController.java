package fr.maif.forknow.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.maif.forknow.dto.RegisterDto;
import fr.maif.forknow.model.User;
import fr.maif.forknow.service.UserService;
import jakarta.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        Optional<User> user = userService.from(authentication);
        if(user.isPresent()){
            model.addAttribute("currentUser", user.get());
        }
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute RegisterDto userMapping) {
        System.out.println("Ok");
        if(!userMapping.getPassword().equals(userMapping.getPasswordConfirm())){
            return "redirect:/register?error";
        }
        userService.saveUser(userMapping);
        return "redirect:/login";
    }
    
}
