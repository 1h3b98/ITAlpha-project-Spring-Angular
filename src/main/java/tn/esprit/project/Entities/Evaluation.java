package tn.esprit.project.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Evaluation implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Description;
    @Enumerated(EnumType.STRING)
    private Badge badge;
   
    
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "evaluation")
    private List<Trophy> trophies;

}
