package tn.esprit.project.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 Long userId;
	 String FName;
	 String LName;
	 boolean sexe;
	 String email;
	 String username;
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
	 
	 @OneToMany( mappedBy="userP")
	 private List<Post> Posts;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userComment")
	 private List<Comment> Comments;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userAction")
	 private List<Action> actions;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userForum")
	 private List<Forum> forums;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userOpinion")
	 private List<Opinion> Opinions;
	 

	 
	 @ManyToOne
	 private Vote vote;
	 
	 @OneToMany (mappedBy ="user")
	 List<Notification> notifications;
	 

	 @ManyToOne 
	 Departement department;
	 
	 @OneToMany(mappedBy = "sender")
	 List<Message> msgSent;
	 
	 @OneToMany(mappedBy ="reciever")
	 List<Message> msgRecieved;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Event> FavEvents;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="userL")
	private List<Event> rEvents;


	 
	 @OneToMany(mappedBy = "sender")
	  List<FeedBack> feedBacksent;

	 @OneToMany(mappedBy = "reciever")
	List<FeedBack> feedbackrecieved;

	 @OneToOne(mappedBy = "user")
	private Evaluation evaluation;


	 @OneToMany(mappedBy = "user")
	List<Score> scores;
}
