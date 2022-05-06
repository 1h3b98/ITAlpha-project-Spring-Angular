package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date date;
    private Date validateAt;
    private Boolean stat;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    PubliciteOffre publiciteOffre;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    User user;
}
