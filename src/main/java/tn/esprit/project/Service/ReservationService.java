package tn.esprit.project.Service;


import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Reservation;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PubliciteRepo;
import tn.esprit.project.Repository.ReservationRepo;
import tn.esprit.project.Repository.UserRepo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReservationService {


    private final ReservationRepo reservationRepo;
    private final UserRepo userRepo;
    private final PubliciteRepo publiciteRepo;

    public Reservation addReservation(Long idu, Long idp) {
        User user = userRepo.findById(idu).get();
        PubliciteOffre publiciteOffre = publiciteRepo.findById(idp).get();
        Reservation reservation =new Reservation();
        reservation.setDate(new Date());
        reservation.setUser(user);
        reservation.setPubliciteOffre(publiciteOffre);
        return reservationRepo.save(reservation);
    }
    public void DeleteReservation(Long id) {
        reservationRepo.deleteById(id);
    }
    public List<Reservation> getALL() {
        List<Reservation> reservationList = reservationRepo.findAll();
        return reservationList;
    }
    public List<Reservation> findbydate(Date date) {
        return reservationRepo.findReservationByDate(date);
    }
    public Reservation Update(Reservation reservation) {
        Reservation reservation1 = reservationRepo.findById(reservation.getId()).get();
        reservation1.setDate(reservation.getDate());
        return reservationRepo.save(reservation);
    }
    public List<Reservation> getbestparticpte(String titre){
        List<Reservation> reservationList=reservationRepo.findAll();
       return reservationList.stream().filter(v->v.getPubliciteOffre().equals(titre)).sorted((u,v)->countReservztion(u.getUser().getUserId(),titre)-countReservztion(v.getUser().getUserId(),titre)).collect(Collectors.toList());

    }
    @Query("select count(r) from Reservation r where r.user=:id and r.publiciteOffre.titre=:titre")
    public int countReservztion(Long id, String titre) {
        return reservationRepo.countReservztion(id, titre);
    }

}
