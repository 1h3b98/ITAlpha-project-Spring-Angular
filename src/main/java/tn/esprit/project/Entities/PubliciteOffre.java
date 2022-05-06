package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date datedebut;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdat;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateexpir;
    private int nbrevue;
    private String image;
    private String mail;
    private String num;
    private Boolean status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "publiciteOffre")
    List<Reservation> reservationList;
    @OneToMany(mappedBy = "publiciteOffre",cascade = CascadeType.ALL)
    List<Rating> ratingList;
}
