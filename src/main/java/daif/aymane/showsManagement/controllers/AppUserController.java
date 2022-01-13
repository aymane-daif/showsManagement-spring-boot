package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.services.AppUserService;
import daif.aymane.showsManagement.dto.users.ResponseObject;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(path = "api/v1/users")
@RestController
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public ResponseObject getUsers(){
        return appUserService.allUsers();
    }

    @GetMapping("/{userId}")
    public ResponseObject getUser(@PathVariable Long userId){
        return appUserService.getUser(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseObject deleteUsers(@PathVariable Long userId){
        return appUserService.removeUser(userId);
    }

}
