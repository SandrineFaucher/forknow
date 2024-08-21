package fr.maif.forknow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import fr.maif.forknow.model.User;
// import fr.maif.forknow.service.UserService;

@Service
public class UserSecurityService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public UserSecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                        .orElseThrow(()->new UsernameNotFoundException("User didn't exist."));

        return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().name())
                    .build();
    }

}
