package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post implements Serializable  {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long IdPost;
	String Content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdateAt", nullable = true)
	Date UpdateAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateAt", nullable = true)
	Date CreateAt;
	int nbrComment;
	float note;


	@JsonIgnore
	@ManyToOne
	User userP;

	@OneToOne
	FileDb fileDb;

	
	@ManyToOne
	Categorie categorie;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="post")
	 List<Comment> comments;

}
