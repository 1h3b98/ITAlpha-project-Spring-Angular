package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PubliciteOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String titre;
    @Enumerated(EnumType.STRING)
    private Cible cible;
    private Timestamp datedebut;
    private Timestamp datefin;
    private int nbrevue;
    private String image;
    private String mail;
    private String num;
    private Date happyDay;
    private Boolean status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "publiciteOffre")
    List<Reservation> reservationList;
}
