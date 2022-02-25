package tn.esprit.project.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userP")
	 private List<Post> Posts;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userLike")
	 private List<LikePost> likePosts;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userComment")
	 private List<Comment> Comments;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userAction")
	 private List<Action> actions;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userForum")
	 private List<Forum> forums;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userOpinion")
	 private List<Opinion> Opinions;
	 
	 @OneToMany(cascade = CascadeType.ALL, mappedBy="userLike")
	 private List<LikeComment> likeComments;
	 
	 @ManyToOne
	 private Vote vote;
	 
	 @OneToMany (mappedBy ="user")
	 List<Notification> notifications;
	 
	 @OneToMany (mappedBy="user")
	 List<ClassBadge> badges;
	 
	 @ManyToOne 
	 Departement department;
	 
	 @OneToMany(mappedBy = "sender")
	 List<Message> msgSent;
	 
	 @OneToMany(mappedBy ="reciever")
	 List<Message> msgRecieved;
	 
	 
	 
	 
}
