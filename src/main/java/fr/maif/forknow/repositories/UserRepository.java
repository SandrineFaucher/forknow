package fr.maif.forknow.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.maif.forknow.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>findByUsername(String username);

    Optional<User> findByEmail(String email);
}
