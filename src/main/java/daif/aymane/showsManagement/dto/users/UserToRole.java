package daif.aymane.showsManagement.dto.users;

public class UserToRole {
    private String username;
    private String roleName;

    public UserToRole() {
    }

    public UserToRole(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
