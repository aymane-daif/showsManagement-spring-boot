package daif.aymane.showsManagement.dto.users;

import daif.aymane.showsManagement.models.TVShow;
import daif.aymane.showsManagement.models.UserRole;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private Collection<UserRole> userRoles = new ArrayList<>();
    private int totalTvShows = 0;

    public UserResponse(){}

    public UserResponse(Long userId, String username, String email, Collection<UserRole> userRoles, int totalTvShows) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.userRoles = userRoles;
        this.totalTvShows = totalTvShows;
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

    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public int getTotalTvShows() {
        return totalTvShows;
    }

    public void setTotalTvShows(int totalTvShows) {
        this.totalTvShows = totalTvShows;
    }
}
