package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","FavEvents"})

public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String FName;
    String LName;
    Long Cin;
    @Enumerated(EnumType.STRING)
    Sexe sexe;
    String email;
    String username;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date birthDate;
    int phonenumbr;
    String jobTitle;
    String picture;
    @Enumerated(EnumType.STRING)
    Hobbies hobbies;
    String adresse;
    String bio;
    int points;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private List<QvtAnswer> QVTAnswers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userP")
    private List<Post> Posts;
    
    @ManyToMany
    @JsonIgnore
    private List<Event> FavEvents;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy="userL")
    private List<Event> Events;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userLike")
    private List<LikePost> likePosts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userComment")
    private List<Comment> Comments;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="userAction")
    private List<Action> actions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userForum")
    private List<Forum> forums;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userOpinion")
    private List<tn.esprit.project.Entities.Opinion> Opinions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="userLike")
    private List<tn.esprit.project.Entities.LikeComment> likeComments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vote vote;

    @OneToMany (mappedBy ="user")
    List<Notification> notifications;

    @OneToMany (mappedBy="user")
    List<ClassBadge> badges;

    @ManyToOne(cascade = CascadeType.ALL)
    Departement department;

    @OneToMany(mappedBy = "sender")
    List<Message> msgSent;

    @OneToMany(mappedBy ="reciever")
    List<Message> msgRecieved;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<PubliciteOffre> publiciteLis;
    @OneToMany(mappedBy = "user")
            @JsonIgnore
    List<Reservation> reservationList;


}
