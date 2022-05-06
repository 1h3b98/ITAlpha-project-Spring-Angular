package tn.esprit.project.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.project.Entities.Departement;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Entities.User;

import tn.esprit.project.Registration.ConfirmationToken;
import tn.esprit.project.Registration.ConfirmationTokenService;
import tn.esprit.project.Repository.DepartementRepo;
import tn.esprit.project.Repository.RoleRepo;
import tn.esprit.project.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService  {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final DepartementRepo departementRepo;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";


    @Autowired
    UserRepo ur ;
    @Autowired
    NotificationRepository nr ;
    @Autowired
    EventRepository er;

    @Scheduled(fixedRate = 10000)
    public void birthdayUsers(){
        Long iduser = Long.valueOf(1);
        List<User>users = (List<User>) ur.findAll();
        Date newdate =new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(newdate);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        log.info("day"+String.valueOf(day));
        log.info("year"+String.valueOf(year));
        log.info("month"+String.valueOf(month));
        log.info("------------------------------");
        Calendar c1 = Calendar.getInstance();
        for (User u:users) {
            c1.setTime(u.getBirthDate());
            int dayuser = c1.get(Calendar.DAY_OF_MONTH);
            int yearuser = c1.get(Calendar.YEAR);
            int monthuser = c1.get(Calendar.MONTH);
            log.info("day"+String.valueOf(dayuser));
            log.info("year"+String.valueOf(yearuser));
            log.info("month"+String.valueOf(monthuser));
            if(day==dayuser&& monthuser==month && u.getUserId()!=iduser){
                Notification notification = new Notification();
                notification.setUser(ur.findById(iduser).get());
                notification.setNotDate((new Timestamp(newdate.getTime())));
                notification.setContent("anniversaire de :"+u.getFirstName()+" -- age :"+(year-yearuser));
                nr.save(notification);
            }
            if(day==dayuser&& monthuser==month && u.getUserId()==iduser){
                Notification notification = new Notification();
                notification.setUser(ur.findById(iduser).get());
                notification.setNotDate((new Timestamp(newdate.getTime())));
                notification.setContent("ton anniversaire :"+u.getFirstName()+"-- age :"+(year-yearuser));
                nr.save(notification);
            }

        }
    }
    public User addFav(long eventId,long userId){
        User userToupdate;
        Event eventInuser;
        userToupdate=ur.findById(userId).orElse(null);
        eventInuser=er.findById(eventId).orElse(null);
        assert userToupdate != null;
        userToupdate.getFavEvents().add(eventInuser);
        return ur.save(userToupdate);
    }

    public User removeFav(long eventId,long userId){
        User userToupdate=ur.findById(userId).orElse(null);
        Event eventInuser=er.findById(eventId).orElse(null);
        assert userToupdate != null;
        userToupdate.getFavEvents().remove(eventInuser);
        return ur.save(userToupdate);
    }


    public void DeleterUser(Long id) {

        userRepo.deleteById(id);
    }


    public User Complétiondeprofil(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }



    public Role saveRole(Role role) {
        log.info("saving role to database", role.getName());
        return roleRepo.save(role);
    }
    public void addRoleToUser(String email, String RoleName) {
        User user = userRepo.findUserByEmail(email);
        Role role = (Role) roleRepo.findByName(RoleName);
        user.getRoles().add(role);
    }
    public User findbyemail(String email) {

        return userRepo.findUserByEmail(email);
    }

    public User findbyusername(String username) {

        return userRepo.findByUsername(username);
    }

    public Departement Adddpt(Departement departement) {

        return departementRepo.save(departement);
    }

    public void affecterdptauser(String username, String title) {
        User user = userRepo.findByUsername(username);
        Departement departement = departementRepo.findDepartementByTitle(title);
        user.setDepartment(departement);
    }






    public String signUpUser(User user) {
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

        User user = userRepo.findByUsername(username);
        user.setIsBlocked(true);
        userRepo.save(user);
    }

    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }
    public User getUser(Long id){
        return userRepo.findById(id).get();
    }
    public List<User> getAll(){
        return (List<User>) userRepo.findAll();
    }
    public User getuser(Long id){
        return userRepo.findById(id).get();
    }
}