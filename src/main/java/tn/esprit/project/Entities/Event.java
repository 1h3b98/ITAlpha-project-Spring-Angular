package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long eventId;
    String title;
    Timestamp startTime;
    Timestamp endTime;
    String img;
    int joinnbr;
    int likenbr;
    int eventReward;


    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> userf;

    @ManyToMany
    private List<User> userL;
    @OneToMany()
    @JsonIgnore
    List<Action> actions;

    @OneToMany()
    List<Reward> eventrewards;


}
