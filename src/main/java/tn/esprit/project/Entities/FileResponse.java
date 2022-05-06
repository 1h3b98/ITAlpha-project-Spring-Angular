package tn.esprit.project.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileResponse {
    private String id;

    private String type;

    private String name;
    private  Post post;

}
