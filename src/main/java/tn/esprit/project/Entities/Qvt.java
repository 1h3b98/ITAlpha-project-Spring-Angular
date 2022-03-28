package tn.esprit.project.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qvt implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qvtId;
	private String content;
	private float tauxDeReponse;

	@OneToMany(cascade = CascadeType.ALL, mappedBy="question")
	 private List<QvtAnswer> QVTAnswers;
	@ManyToOne
	User userCretateur;
}
