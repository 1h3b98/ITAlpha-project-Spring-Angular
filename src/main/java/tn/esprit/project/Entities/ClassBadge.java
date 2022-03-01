package tn.esprit.project.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class ClassBadge  implements Serializable {
	 
		private static final long serialVersionUID = 1L;
		@Id 
		@GeneratedValue (strategy = GenerationType.IDENTITY)
		Long Id;
		String description;
		tn.esprit.project.Entities.Badge badge;
		
		@ManyToOne
		User user;
		
		@ManyToOne
		tn.esprit.project.Entities.Evaluation evaluation;

}
