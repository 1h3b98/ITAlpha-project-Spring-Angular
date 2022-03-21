package tn.esprit.project.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Signaler implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    SignalerId SignalerId;
    @Enumerated(EnumType.STRING)
    SingalerType singalerType;
    String description;
}
