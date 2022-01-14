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

    @GetMapping("/{username}")
    public ResponseObject getUser(@PathVariable String username){
        return appUserService.getUser(username);
    }

    @DeleteMapping("/{username}")
    public ResponseObject deleteUsers(@PathVariable String username){
        return appUserService.removeUser(username);
    }

}
