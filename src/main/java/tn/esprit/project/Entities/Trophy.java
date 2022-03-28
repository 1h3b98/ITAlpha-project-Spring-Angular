package tn.esprit.project.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trophy {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String Description;
    TrophyType trophytype;
    @Temporal(TemporalType.DATE)
    Date DateTrophey;
  @ManyToOne
    private Evaluation evaluation;


}
