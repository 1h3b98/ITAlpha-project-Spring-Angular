package tn.esprit.project.Contoller;



import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.esprit.project.Entities.Departement;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Service.UserService;

import java.net.URI;
import java.util.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserRepo userRepo;
    private final UserService userService;

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
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser(){

        return ResponseEntity.ok().body(userService.getAll());
    }
//    @PostMapping("/user/save")
//   public ResponseEntity<User> saveUser(@RequestBody User user){
//        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        User user1=userService.findbyusername(user.getUsername());
//       if(user1!=null) throw new RuntimeException("user exist");
//        return ResponseEntity.created(uri).body(userService.addUser(user));
//  }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser/{username}/{RoleName}")
    public void addRoleToUser(@PathVariable("username") String usernamen,@PathVariable("RoleName") String RoleName) {
        userService.addRoleToUser(usernamen,RoleName);

    }
    @PostMapping("/addepartement")
    public Departement Adddpt(@RequestBody Departement departement) {
        return userService.Adddpt(departement);
    }

    @PutMapping("affecterdpttouser/{username}/{title}")
    public void affecterdptauser(@PathVariable("username") String username,@PathVariable("title") String title) {

        userService.affecterdptauser(username, title);
    }
    @DeleteMapping("delete/{id}")
    public void DeleterUser(@PathVariable("id") Long id) {
        userService.DeleterUser(id);
    }
//    @PutMapping("/role/affectionate/{idu}/{idr}")
//    public void affecterusertorole(@PathVariable("idu") Long idu,@PathVariable("idr") Long idr){
//        userService.affecterRoletoUser(idu,idr);
//   }
//    @GetMapping("/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader=request.getHeader(AUTHORIZATION);
//        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
//            try{
//                String refresh_token=authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm=Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier= JWT.require(algorithm).build();
//                DecodedJWT decodedJWT=verifier.verify(refresh_token);
//                String username=decodedJWT.getSubject();
//                User user=userService.getUser(username);
//
//                String access_token= JWT.create().withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 *1000))
//                        .withIssuer(request.getRequestURL().toString())
//                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//                //   response.setHeader("access_token",access_token);
//                //  response.setHeader("refresh_token",refresh_token);
//                Map<String,String > tokens=new HashMap<>();
//                tokens.put("access_token",access_token);
//                tokens.put("refresh_token",refresh_token);
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
//            }catch (Exception exception){
//                response.setHeader("error",exception.getMessage());
//                response.setStatus(FORBIDDEN.value());
//                // response.sendError(FORBIDDEN.value());
//                Map<String,String > error=new HashMap<>();
//                error.put("error_message",exception.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(),error);
//            }
//
//
//
//        }else{
//            throw new RuntimeException("refresh token is missing");
//        }
//    }
}



