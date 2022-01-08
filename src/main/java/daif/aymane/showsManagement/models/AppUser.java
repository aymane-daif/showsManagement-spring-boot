package daif.aymane.showsManagement.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(unique = true)
    private String username;
    private String email;
    private String encryptedPassword;
    // every time when we load user we want to load roles
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRole> userRoles = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
    private List<TVShow> TVShows = new ArrayList<>();

    public AppUser(){}

    public AppUser(Long userId, String username, String email, String encryptedPassword, Collection<UserRole> userRoles, List<TVShow> TVShows) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.userRoles = userRoles;
        this.TVShows = TVShows;
    }

    public AppUser(String username, String email, String encryptedPassword, Collection<UserRole> userRoles, List<TVShow> TVShows) {
        this.username = username;
        this.email = email;
        this.encryptedPassword = encryptedPassword;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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
