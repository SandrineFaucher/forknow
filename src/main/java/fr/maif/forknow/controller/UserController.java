package fr.maif.forknow.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import fr.maif.forknow.model.User;
import fr.maif.forknow.repositories.UserRepository;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Long id) throws NotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }

    @PostMapping("/user")
    public User createNewUser(@ModelAttribute("username") String username,
            @ModelAttribute("displayName") String displayName,
            @ModelAttribute("email") String email,
            @ModelAttribute("password") String password,
            @ModelAttribute("creationDate") String creationDate) {
        User user = new User();
        System.out.println(user);
        User newUser = this.userRepository.save(user);
        return newUser;
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        // Utilisation de l'Optional pour trouver l'utilisateur
        Optional<User> optionalUser = userRepository.findById(id);

        // Vérification si l'utilisateur existe
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Mise à jour des propriétés de l'utilisateur
            user.setUsername(updatedUser.getUsername());
            user.setDisplayName(updatedUser.getDisplayName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setCreationDate(updatedUser.getCreationDate());

            // Sauvegarde de l'utilisateur mis à jour
            return userRepository.save(user);
        } else {
            // Gestion du cas où l'utilisateur n'est pas trouvé
            throw new ResourceAccessException("User not found with id " + id);
        }
    }

    @DeleteMapping("/user/{userId}")

    public void deleteUser(@PathVariable("userId") Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceAccessException("User not found with id "));
        userRepository.delete(user);
    }
}
