package tn.esprit.project.Contoller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Service.RoleService;
import tn.esprit.project.Service.UserService;
import tn.esprit.project.Entities.User;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final RoleService roleService;
    private final   UserService userService;

    @GetMapping("/getuserbyname/{name}")
    public List<User> getByName(@PathVariable("name") String name) {

        return userService.getByName(name);
    }

    @PostMapping("/ajouterole")
    public Role addrole(@RequestBody Role role) {

        return roleService.addrole(role);
    }


    @DeleteMapping("Delete/{id}")
    public void DeleteUser(@PathVariable("id") Long id) {

        userService.DeleteUser(id);
    }
    @GetMapping("/getUser")
    public List<User> getAll() {

        return userService.getAll();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }
    @PutMapping("AffecteroletoUser/{idu}/{idr}")
    public User affecterRoletoUser(@PathVariable("idu") Long idUser,@PathVariable("idr") Long idRole) {
        return userService.affecterRoletoUser(idUser, idRole);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.Update(user);
    }




}
