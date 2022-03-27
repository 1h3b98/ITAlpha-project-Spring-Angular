package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
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
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	long eventId;
	String title;
	Timestamp startTime;
	Timestamp endTime;
	String img;
	String offers;
	int likenbr;
	String eventReward;
	
	@OneToMany
	List<Action> actions;
	
	@OneToMany(mappedBy ="event")
	List<Reward> eventrewards;
	@ManyToMany
	private List<User> userL;
}
