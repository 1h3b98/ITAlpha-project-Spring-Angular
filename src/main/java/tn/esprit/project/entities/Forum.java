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
	int Rating ;
	int signaler;
	
	@ManyToOne
	User userForum;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="forumOpinion")
	 private List<Opinion> Opinions;
	
	@OneToMany(mappedBy = "forum")
	List<ForumTag> forumtags;
	
	
}
