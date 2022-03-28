package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
public class Forum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long IdForum;
	String Content;
	String Title;
	Timestamp UpdateAt;
	Timestamp CreateAt;
	Long NbLike;
	Long NbComment;
	float Rating ;
	int signaler;

	@JsonIgnore
	@ManyToOne
	User userForum;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="forumOpinion")
	 private List<Opinion> Opinions;
	
	@ManyToMany(mappedBy = "forum")
	List<ForumTag> forumtags;
	
	
}
