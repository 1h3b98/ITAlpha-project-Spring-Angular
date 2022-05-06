package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;



import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {// implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long userId;
	String firstName;
	String lastName;
	String password;
	Boolean isBlocked ;
	Long Cin;

	String sexe;
	String email;
	String username;
	@JsonFormat(pattern = "yyyy-mm-dd")
	Date birthDate;
	String phonenumbr;
	String jobTitle;
	String picture;
	@Enumerated(EnumType.STRING)
	Hobbies hobbies;
	String adresse;
	String bio;
	String oneTimePassword;
	int points;
	private Boolean locked ;
	private Boolean enabled =false;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	Departement department;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	List<PubliciteOffre> publiciteLis;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	List<Reservation> reservationList;
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

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<Score> scoree;



	@ManyToOne
	private Vote vote;

	@OneToMany (mappedBy ="user")
	List<Notification> notifications;



	public User(Long userId, String password, String username, List<Role> roles) {
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.roles = roles;
	}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.password=password;

	}





	public User(OtpStatus delivered, String otpMessage) {
	}


	public String getEmail() {
		return this.email;
	}



}
