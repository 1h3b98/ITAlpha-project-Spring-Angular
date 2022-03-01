package tn.esprit.project.Contoller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Service.RoleService;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;
    @PutMapping("/Updaterole")
    public Role Updaterole(@RequestBody Role role) {
        return roleService.Updaterole(role);
    }
    @GetMapping("/getAllRole")
    public List<Role> getAll() {
        return roleService.getAll();
    }
    @GetMapping("/getRoleid/{id}")
    public Role getRole(@PathVariable("id") Long id) {
        return roleService.getRole(id);
    }
    @PostMapping("/addrol")
    public Role addrole(@RequestBody Role role) {

        return roleService.addrole(role);
    }
    @DeleteMapping("/Deleterole/{id}")
    public void DeleteRole(@PathVariable("id") Long id) {

        roleService.DeleteRole(id);
    }
    @GetMapping("/getALLrole")
    public List<Role> getALL() {

        return roleService.getALL();
    }


}
