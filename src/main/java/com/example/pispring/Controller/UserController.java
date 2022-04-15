package com.example.pispring.Controller;



import com.example.pispring.Entities.Departement;
import com.example.pispring.Entities.Role;
import com.example.pispring.Entities.User;
import com.example.pispring.Repository.UserRepo;
import com.example.pispring.Service.TwilioOTPService;
import com.example.pispring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


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



