package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.users.ResponseObject;
import daif.aymane.showsManagement.dto.users.UserRequest;
import daif.aymane.showsManagement.services.AppUserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(path = "api/v1")
@RestController
public class AuthController {
    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseObject createUser(@RequestBody UserRequest userRequest){
        return appUserService.addUser(userRequest);
    }

    @PostMapping("/login")
    public ResponseObject logUserIn(@RequestBody UserRequest userRequest){
        return appUserService.loginUser(userRequest);
    }
}
