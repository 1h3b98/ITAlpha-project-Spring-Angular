package tn.esprit.project.Entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","userL"})


public class Event implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	long eventId;
	String title;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Timestamp startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Timestamp endTime;
	String img;
	String about;
	int joinnbr;
	int likenbr;
	int eventReward;


	@ManyToMany(cascade = CascadeType.ALL, mappedBy="FavEvents")
	@JsonIgnore
	private List<User> userf;

	@ManyToMany
	private List<User> userL;
	@OneToMany(mappedBy ="event")
	@JsonIgnore
	List<Action> actions;


	@OneToMany(mappedBy ="event")
	@JsonIgnore
	List<Reward> eventrewards;
	@JsonIgnore
	@OneToOne
	private Quiz quize;
	@OneToOne
	EventDb eventDb;
}
