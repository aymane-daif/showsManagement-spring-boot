package daif.aymane.showsManagement.controllers;

import daif.aymane.showsManagement.dto.users.ResponseObject;
import daif.aymane.showsManagement.models.AppUser;
import daif.aymane.showsManagement.services.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RequestMapping(path = "api/v1")
@RestController
public class AuthController {
    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> createUser(@Valid @RequestBody AppUser appUser){
        return new ResponseEntity<ResponseObject>( appUserService.addUser(appUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseObject logUserIn(@Valid @RequestBody AppUser appUser){
        return appUserService.loginUser(appUser);
    }
}
