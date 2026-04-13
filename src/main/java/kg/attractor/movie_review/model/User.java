package kg.attractor.movie_review.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usr")
public class User {
    @Id
    private String email;
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;
}
