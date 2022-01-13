package daif.aymane.showsManagement.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @Size(min = 2, message = "username must have at least 2 characters")
    @NotNull(message = "username cannot be null")
    private String username;

    @Email
    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, message = "password must have at least 6 characters")
    private String password;

    // every time when we load user we want to load roles
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRole> userRoles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    private List<TVShow> TVShows = new ArrayList<>();

    public AppUser(){}

    public AppUser(Long userId, String username, String email, String password, Collection<UserRole> userRoles, List<TVShow> TVShows) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
        this.TVShows = TVShows;
    }

    public AppUser(String username, String email, String password, Collection<UserRole> userRoles, List<TVShow> TVShows) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRoles = userRoles;
        this.TVShows = TVShows;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public List<TVShow> getTVShows() {
        return TVShows;
    }

    public void setTVShows(List<TVShow> TVShows) {
        this.TVShows = TVShows;
    }
}
