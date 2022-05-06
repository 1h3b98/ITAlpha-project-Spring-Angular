package tn.esprit.project.Contoller;



import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import tn.esprit.project.Entities.Departement;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Service.TwilioOTPService;
import tn.esprit.project.Service.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepo userRepo;
    private final TwilioOTPService twilioOTPService;


    private final UserService userService;
    @GetMapping("getuser/{id}")
    public User getuser(@PathVariable("id") Long id) {
        return userService.getuser(id);
    }


    @GetMapping("getAlluser")
    public List<User> getAll() {
        return userService.getAll();
    }
    @PostMapping("SendSMSForgetPassword")
    public Mono<User> sendOTPForPasswordReset(@RequestBody User User) {
        return twilioOTPService.sendOTPForPasswordReset(User);
    }
    @PostMapping("ValidateOtp/{userInputOtp}/{username}")
    public Mono<String> validateOTP(@PathVariable("userInputOtp") String userInputOtp,@PathVariable("username") String username) {
        return twilioOTPService.validateOTP(userInputOtp, username);
    }



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
   /* @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody Loginrequest loginrequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginrequest.getUsername(), loginrequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsimpl userDetails = (UserDetailsimpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail()
                ));
    }

    */
   @GetMapping("/token/refresh")
   public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String authorizationHeader = request.getHeader(AUTHORIZATION);
       if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
           try {
               String refresh_token = authorizationHeader.substring("Bearer ".length());
               Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
               JWTVerifier verifier = JWT.require(algorithm).build();
               DecodedJWT decodedJWT = verifier.verify(refresh_token);
               String username = decodedJWT.getSubject();
               User user = userService.getUser(username);
               String access_token = JWT.create()
                       .withSubject(user.getUsername())
                       .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                       .withIssuer(request.getRequestURL().toString())
                       .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                       .sign(algorithm);
               Map<String, String> tokens = new HashMap<>();
               tokens.put("access_token", access_token);
               tokens.put("refresh_token", refresh_token);
               response.setContentType(MediaType.APPLICATION_JSON_VALUE);
               new ObjectMapper().writeValue(response.getOutputStream(), tokens);
           }catch (Exception exception) {
               response.setHeader("error", exception.getMessage());
               response.setStatus(FORBIDDEN.value());
               //response.sendError(FORBIDDEN.value());
               Map<String, String> error = new HashMap<>();
               error.put("error_message", exception.getMessage());
               response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
               new ObjectMapper().writeValue(response.getOutputStream(), error);
           }
       } else {
           throw new RuntimeException("Refresh token is missing");
       }
   }
   @GetMapping("GetUser/{id}")
   public User getUserEmail(@PathVariable("id") Long id){
       return userService.getUser(id);
   }

}



