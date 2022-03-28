package tn.esprit.project.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    String firstName;
    String lastName;
    String password;
    Boolean isBlocked ;
    Long Cin;
    @Enumerated(EnumType.STRING)
    Sexe sexe;
    String email;
    String username;
    @JsonFormat(pattern = "yyyy-mm-dd")
    Date birthDate;
    String phonenumbr;
    String jobTitle;
    String picture;
    @Enumerated(EnumType.STRING)
    Hobbies hobbies;
    String adresse;
    String bio;
    String oneTimePassword;
    int points;
    private Boolean locked =false;
    private Boolean enabled =false;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<QvtAnswer> QVTAnswers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userP")
    private List<Post> Posts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userLike")
    private List<LikePost> likePosts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userComment")
    private List<Comment> Comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAction")
    private List<Action> actions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userForum")
    private List<Forum> forums;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOpinion")
    private List<tn.esprit.project.Entities.Opinion> Opinions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userLike")
    private List<tn.esprit.project.Entities.LikeComment> likeComments;

    @ManyToOne(cascade = CascadeType.ALL)
    private Vote vote;

    @OneToMany(mappedBy = "user")
    List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    List<ClassBadge> badges;

    @ManyToOne(cascade = CascadeType.ALL)
    Departement department;

    @OneToMany(mappedBy = "sender")
    List<Message> msgSent;

    @OneToMany(mappedBy = "reciever")
    List<Message> msgRecieved;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<PubliciteOffre> publiciteLis;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Reservation> reservationList;


    public User(Long userId, String password, String username, List<Role> roles) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password=password;

    }

    public User(OtpStatus delivered, String otpMessage) {
    }

    public String getFName() {
        return firstName;
    }

    public String getLName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public User(String username, String fName, String email, String password, Long cin, Sexe sexe, String adresse, String bio, Date birthDate, int phonenumbr, String jobTitle, String picture, Hobbies hobbies, String lName) {
    }

    @Override
    public String getUsername() {
        return username;
    }
//    public String getUsername(){
//        return username;
//    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//       SimpleGrantedAuthority authority= new SimpleGrantedAuthority(new Role().getName());
//
//        return Collections.singletonList(authority);
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority= new SimpleGrantedAuthority(new Role().getName());
//
//        return Collections.singletonList(authority);
//    }


}
