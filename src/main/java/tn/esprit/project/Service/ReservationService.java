package tn.esprit.project.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.PubliciteOffre;
import tn.esprit.project.Entities.Reservation;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.PubliciteRepo;
import tn.esprit.project.Repository.ReservationRepo;
import tn.esprit.project.Repository.UserRepo;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    private final UserRepo userRepo;
    private final PubliciteRepo publiciteRepo;

    public Reservation addReservation(Reservation reservation, Long idu, Long idp) {
        User user = userRepo.findById(idu).get();
        PubliciteOffre publiciteOffre = publiciteRepo.findById(idp).get();
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

}
