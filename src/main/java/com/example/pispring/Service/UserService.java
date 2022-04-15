package com.example.pispring.Service;

import com.example.pispring.Entities.Departement;
import com.example.pispring.Entities.Role;
import com.example.pispring.Registration.ConfirmationToken;
import com.example.pispring.Registration.ConfirmationTokenService;
import com.example.pispring.Repository.DepartementRepo;
import com.example.pispring.Repository.RoleRepo;
import com.example.pispring.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final DepartementRepo departementRepo;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";


    public List<com.example.pispring.Entities.User> getAll() {
        return userRepo.findAll();
    }
    public void DeleterUser(Long id) {

        userRepo.deleteById(id);
    }


    public com.example.pispring.Entities.User Complétiondeprofil(com.example.pispring.Entities.User user) {
        com.example.pispring.Entities.User user1 = userRepo.findById(user.getUserId()).get();
        user1.setAdresse(user.getAdresse());
        user1.setBio(user.getBio());
        user1.setCin(user.getCin());
        user1.setBirthDate(user.getBirthDate());
        user1.setFirstName(user.getFirstName());
        user1.setUsername(user.getUsername());
        user1.setLastName(user.getLastName());
        user1.setHobbies(user.getHobbies());
        user1.setSexe(user.getSexe());
        user1.setJobTitle(user.getJobTitle());
        user1.setPhonenumbr(user.getPhonenumbr());
        user1.setPoints(user.getPoints());
        user1.setDepartment(user.getDepartment());
       // user1.setVote(user.getVote());
        user1.setPicture(user.getPicture());


        return userRepo.save(user1);
    }
    public Role saveRole(Role role) {
        log.info("saving role to database", role.getName());
        return roleRepo.save(role);
    }
    public void addRoleToUser(String email, String RoleName) {
        com.example.pispring.Entities.User user = userRepo.findUserByEmail(email);
        Role role = (Role) roleRepo.findByName(RoleName);
        user.getRoles().add(role);
    }
    public com.example.pispring.Entities.User findbyemail(String email) {

        return userRepo.findUserByEmail(email);
    }

    public com.example.pispring.Entities.User findbyusername(String username) {

        return userRepo.findByUsername(username);
    }

    public Departement Adddpt(Departement departement) {

        return departementRepo.save(departement);
    }

    public void affecterdptauser(String username, String title) {
        com.example.pispring.Entities.User user = userRepo.findByUsername(username);
        Departement departement = departementRepo.findDepartementByTitle(title);
        user.setDepartment(departement);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.pispring.Entities.User user = userRepo.findByUsername(username);
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

    public String signUpUser(com.example.pispring.Entities.User user) {
        boolean userExists = userRepo
                .findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("email already taken");
        }
        user.setRoles(roleRepo.findByName("user"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return userRepo.enableUser(email);
    }

    public Role findbyenamme(String name) {

        return (Role) roleRepo.findByName(name);
    }
    public void blockedUser(String username) {

        com.example.pispring.Entities.User user = userRepo.findByUsername(username);
        user.setIsBlocked(true);
        userRepo.save(user);
    }
    public com.example.pispring.Entities.User UserConnecte(Authentication authentication){
        User user= (User) authentication.getPrincipal();
    return  userRepo.findByUsername(user.getUsername());

    }

}