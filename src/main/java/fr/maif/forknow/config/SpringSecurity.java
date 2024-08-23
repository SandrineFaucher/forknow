package fr.maif.forknow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity

public class SpringSecurity {
    private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;
        
@Autowired
public SpringSecurity(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
this.userDetailsService = userDetailsService;
this.passwordEncoder = passwordEncoder;
    }


@Bean
public static PasswordEncoder passwordEncoder(){
return new BCryptPasswordEncoder();
    }
    
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
return http.authorizeHttpRequests((auth) -> {
        auth
                .requestMatchers("/").permitAll()  // La page d'index est accessible à tous
                .requestMatchers("/register/**").anonymous() // Seulement accessible aux utilisateurs non authentifiés
                .requestMatchers("/restaurant-list/**").authenticated() // accessible seulement aux utilisateurs authentifiés
                .requestMatchers("/restaurant/**").authenticated() // accessible aux utlisateurs authentifiés
                .requestMatchers("/admin").hasRole("ADMIN") // accessible avec le rôle ADMIN
                .requestMatchers("/css/**", "/favicon.ico").permitAll() // Ressources statiques accessibles à tous
                .requestMatchers("/images/**").permitAll(); // permettre l'affichage des images pour tous
                
    }).formLogin(login -> login
            .loginPage("/login") // Définir la page de login personnalisée si nécessaire
            .defaultSuccessUrl("/restaurant-list", true) // Redirige vers la page des restaurants après une authentification réussie
            .permitAll())
    .logout(logout -> logout
            .logoutSuccessUrl("/") // Redirige vers la page de login après une déconnexion
            .permitAll())
    .build();
    }

@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
auth
    .userDetailsService(userDetailsService)
    .passwordEncoder(passwordEncoder);
    }
}
