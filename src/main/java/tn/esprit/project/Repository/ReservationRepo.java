package tn.esprit.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.project.Entities.Reservation;


import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findReservationByDate(Date date);
    @Query("select count(r) from Reservation r where r.user.userId=:id and r.publiciteOffre.titre=:titre")
    public int countReservztion(@Param("id") Long id,@Param("titre") String titre);

}
