package tn.esprit.project.Service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.Role;
import tn.esprit.project.Repository.EventRepository;
import tn.esprit.project.Repository.RoleRepo;
import tn.esprit.project.Repository.UserRepo;
import tn.esprit.project.Entities.User;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
@Autowired
EventRepository er;

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    public User addUser(User user){


        return userRepo.save(user);
    }
    public void DeleteUser(Long id){

        userRepo.deleteById(id);
    }
    public List<User> getAll(){

        return userRepo.findAll();
    }

     public List<User> getByName(String name){
        List<User> userList =userRepo.findByFName(name);
        return  userList;
     }
     public User Update(User user){
        User user1 = userRepo.findById(user.getUserId()).get();
       user1.setAdresse(user.getAdresse());
       user1.setBio(user.getBio());
       user1.setCin(user.getCin());
       user1.setBirthDate(user.getBirthDate());
       user1.setEmail(user.getEmail());
       user1.setFName(user.getFName());
       user1.setUsername(user.getUsername());
       user1.setLName(user.getLName());
       user1.setHobbies(user.getHobbies());
       user1.setSexe(user.getSexe());
       user1.setJobTitle(user.getJobTitle());
       user1.setPhonenumbr(user.getPhonenumbr());
       user1.setPoints(user.getPoints());
       user1.setDepartment(user.getDepartment());
       user1.setVote(user.getVote());

        return userRepo.save(user1);
     }
     public User affecterRoletoUser(Long idUser,Long idRole ){
        User user1=userRepo.findById(idUser).get();
        Role role1=roleRepo.findById(idRole).get();
        user1.getRoles().add(role1);
        return userRepo.save(user1);
     }
     public User addFav(long eventId,long userId){
 		User userToupdate;
 		Event eventInuser;
 		userToupdate=userRepo.findById(userId).orElse(null);
 		eventInuser=er.findById(eventId).orElse(null);
 		assert userToupdate != null;
 		userToupdate.getFavEvents().add(eventInuser);
 		return userRepo.save(userToupdate);
 	}
     
 	public User removeFav(long eventId,long userId){
 		User userToupdate=userRepo.findById(userId).orElse(null);
 		Event eventInuser=er.findById(eventId).orElse(null);
 		assert userToupdate != null;
 		userToupdate.getFavEvents().remove(eventInuser);
 		return userRepo.save(userToupdate);
 	}

}
