package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
public class Comment implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long IdComment;
	String Content;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdateAt", nullable = true)
	Date UpdateAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateAt", nullable = true)
	Date CreateAt;
	Long NbLike;
	Boolean statut ;
	int signaler;
	@JsonIgnore
	@ManyToOne
	User userComment;


	@JsonIgnore
	@ManyToOne
	Post post;
	
	

}
