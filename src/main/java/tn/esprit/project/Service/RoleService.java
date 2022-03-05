package tn.esprit.project.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Repository.RoleRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {
private final     RoleRepo roleRepo;

    public Role addrole(Role role){
        return roleRepo.save(role);  }

    public void DeleteRole(Long id){
        roleRepo.deleteById(id);
    }
    public List<Role> getALL(){
        return roleRepo.findAll();
    }
    public Role  Updaterole(Role role){
        Role role1=roleRepo.findById(role.getId()).get();
        role1.setRole(role.getRole());
        return roleRepo.save(role1);
    }
    public List<Role> getAll(){
        List<Role> roleList=roleRepo.findAll();
        return roleList;
    }
    public Role getRole(Long id){
        return roleRepo.findById(id).get();
    }

}
