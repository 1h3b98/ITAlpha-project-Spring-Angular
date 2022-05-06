package tn.esprit.project.Contoller;


import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;
import tn.esprit.project.Entities.Reservation;
import tn.esprit.project.Repository.ReservationRepo;
import tn.esprit.project.Service.ReservationService;


import javax.validation.GroupSequence;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ReservationController {
    private final ReservationRepo reservationRepo;

    private final ReservationService reservationService;

    @GetMapping("getbestparticpte/{titre}")
    public List<Reservation> getbestparticpte(@PathVariable("titre") String titre) {
        return reservationService.getbestparticpte(titre);
    }

    @GetMapping("countReservztion/{titre}/{id}")

    public int countReservztion(@PathVariable("id") Long id,@PathVariable("titre") String titre) {
        return reservationRepo.countReservztion(id,titre);
    }



    @PostMapping("/addReservation/{idu}/{idp}")
    public Reservation addReservation(@PathVariable("idu") Long idu, @PathVariable("idp") Long idp) {
        return reservationService.addReservation(idu, idp);
    }

    @DeleteMapping("DeleteReservation/{id}")
    public void DeleteReservation(@PathVariable("id") Long id) {
        reservationService.DeleteReservation(id);
    }

    @GetMapping("getALL")
    public List<Reservation> getALL() {
        return reservationService.getALL();
    }

    @GetMapping("getBydate/{date}")
    public List<Reservation> findbydate(@PathVariable("date") Date date) {
        return reservationService.findbydate(date);
    }

    @PutMapping("/updatereservation")
    public Reservation Update(@RequestBody Reservation reservation) {
        return reservationService.Update(reservation);
    }


}
