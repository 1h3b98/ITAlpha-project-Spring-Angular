package tn.esprit.project.Contoller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;
import tn.esprit.project.Entities.Departement;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Service.TwilioOTPService;
import tn.esprit.project.Service.UserService;

import java.net.URI;
import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;
    @PostMapping("SendSMSForgetPassword")
    public Mono<User> sendOTPForPasswordReset(@RequestBody User User) {
        return twilioOTPService.sendOTPForPasswordReset(User);
    }
    @PostMapping("ValidateOtp/{userInputOtp}/{username}")
    public Mono<String> validateOTP(@PathVariable("userInputOtp") String userInputOtp,@PathVariable("username") String username) {
        return twilioOTPService.validateOTP(userInputOtp, username);
    }

    private final TwilioOTPService twilioOTPService;

    @GetMapping("getuserbyname/{email}")
    public User findbyemail(String email) {

        return userService.findbyemail(email);
    }

    @GetMapping("getrolebyen/{name}")
    public Role findbyenamme(@PathVariable("name") String name) {

        return userService.findbyenamme(name);
    }
    @PutMapping("completerleprofil")
    public User Complétiondeprofil(@RequestBody User user) {

    return userService.Complétiondeprofil(user);
    }
    @PutMapping("blockedusername/{username}")
    public void blockedUser(@PathVariable("username") String username) {

        userService.blockedUser(username);
    }

    @GetMapping("/getueserbyusername/{username}")
    public User finduername(@PathVariable("username") String username){

    return userRepo.findByUsername(username);
    }

    @PostMapping("/role/save")
    public Role saveRole(@RequestBody Role role){

        return userService.saveRole(role);
    }
    @PostMapping("/role/addtouser/{username}/{RoleName}")
    public void addRoleToUser(@PathVariable("username") String usernamen,@PathVariable("RoleName") String RoleName) {
        userService.addRoleToUser(usernamen,RoleName);

    }
    //vous modifier le path
    @PostMapping("/add-dpt")
    public Departement Adddpt(@RequestBody Departement departement) {

        return userService.Adddpt(departement);
    }
    //vous modifier le pth
    @PutMapping("/assigndepartmenttouser/{username}/{title}")
    public void affecterdptauser(@PathVariable("username") String username,@PathVariable("title") String title) {
        userService.affecterdptauser(username, title);
    }
    @DeleteMapping("delete/{id}")
    public void DeleterUser(@PathVariable("id") Long id) {
        userService.DeleterUser(id);
    }

}



