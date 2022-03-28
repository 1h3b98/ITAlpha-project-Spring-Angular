package tn.esprit.project.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Favoritesarticle implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long idf;
    @ManyToOne(cascade = CascadeType.ALL)
    User user;
    @ManyToOne(cascade = CascadeType.ALL)
    Forum forum;

}
