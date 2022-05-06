package tn.esprit.project.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
@Slf4j
@Service
public class MyuserDetailsService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


            User user = userRepo.findByUsername(username);
            if (user == null) {
                log.info("user not found in database");
                throw new UsernameNotFoundException("user not found in the databse");
            } else {
                log.info("yuser found in the database", username);
            }
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority((role.getName())));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

