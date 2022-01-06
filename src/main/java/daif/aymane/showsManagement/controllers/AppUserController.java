package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.services.AppUserService;
import daif.aymane.showsManagement.dto.ResponseObject;
import daif.aymane.showsManagement.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/register")
    public ResponseObject createUser(@RequestBody UserRequest userRequest){
        return appUserService.addUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseObject deleteUsers(@PathVariable Long userId){
        return appUserService.removeUser(userId);
    }

    @PostMapping("/login")
    public ResponseObject logUserIn(@RequestBody UserRequest userRequest){
        return appUserService.loginUser(userRequest);
    }
}
