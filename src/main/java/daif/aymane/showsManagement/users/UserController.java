package daif.aymane.showsManagement.users;

import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseObject getUsers(){
        return userService.allUsers();
    }

    @PostMapping("/register")
    public ResponseObject createUser(@RequestBody UserRequest userRequest){
        return userService.addUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseObject deleteUsers(@PathVariable Long userId){
        return userService.removeUser(userId);
    }

    @PostMapping("/login")
    public ResponseObject logUserIn(@RequestBody UserRequest userRequest){
        return userService.loginUser(userRequest);
    }
}
