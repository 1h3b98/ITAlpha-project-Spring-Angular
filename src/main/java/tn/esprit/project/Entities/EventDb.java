package tn.esprit.project.Entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="EFILES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDb {
    @Id
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;


    @OneToOne
    Event event;
}
