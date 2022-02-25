package tn.esprit.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Post implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long IdPost;
	String Content;
	Timestamp UpdateAt;
	Timestamp CreateAt;
	int signaler;
	
	@ManyToOne
	User userP;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="postLike")
	 private List<LikePost> Likes;
	
	@ManyToOne
	Categorie categorie;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	 List<Comment> comments;
}
