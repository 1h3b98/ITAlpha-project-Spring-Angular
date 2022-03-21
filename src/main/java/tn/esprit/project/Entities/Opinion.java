package tn.esprit.project.Entities;

import java.io.Serializable;
import java.util.Date;

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
public class Opinion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long IdOpinion;
	String Content;
	int signaler;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdateAt", nullable = true)
	Date UpdateAt;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateAt", nullable = true)
	Date CreateAt;
	@JsonIgnore
	@ManyToOne
	User userOpinion;
	@JsonIgnore
	@ManyToOne
	Forum forumOpinion;
}
