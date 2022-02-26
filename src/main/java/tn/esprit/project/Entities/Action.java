package tn.esprit.project.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Action implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	long actionId;
	@Enumerated(EnumType.STRING)
	ActionType actionType;
	boolean likeStatus;
	String comment;
	boolean joinStatus;
	
	@ManyToOne
	User userAction;
	
	@ManyToOne 
	Event event;
	
	@OneToMany(mappedBy = "action")
	List<Score> scores;
	

}
