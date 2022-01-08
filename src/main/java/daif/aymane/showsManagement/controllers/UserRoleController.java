package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.users.UserToRole;
import daif.aymane.showsManagement.models.UserRole;
import daif.aymane.showsManagement.services.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/roles")
public class UserRoleController {
    private final AppUserService appUserService;

    public UserRoleController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public String addRole(@RequestBody UserRole userRole){
        appUserService.saveRole(userRole);
        return userRole.getRoleName() + " added";
    }

    @PostMapping(path = "users")
    public String addRoleToUser(@RequestBody UserToRole userToRole){
        appUserService.addRoleToUser(userToRole.getUsername(), userToRole.getRoleName());
        return "role "+ userToRole.getRoleName()+" added to user "+userToRole.getUsername();
    }
}

