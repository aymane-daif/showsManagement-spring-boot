package daif.aymane.showsManagement.users;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseObject allUsers(){
        List<UserResponse> usersResponse = copyPropertiesFromDbToResponse();
        String msg = usersResponse.size() + " users";
        ResponseObject responseObject = createResponseObject(msg, true);
        responseObject.setData(usersResponse);
        return responseObject;
    }

    public ResponseObject addUser(UserRequest userRequest){
        ResponseObject responseObject;
        User user = new User();
        String msg;

        BeanUtils.copyProperties(userRequest, user, "password");
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        User createdUser = userRepository.save(user);

        if(userRepository.existsById(user.getId())){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(createdUser, userResponse,"encryptedPassword");
            msg = "user with id " + createdUser.getId() + " is created successfully";
            responseObject = createResponseObject(msg, true);
            responseObject.getData().add(userResponse);
        }else {
            msg =  "user with id " + createdUser.getId() + " is NOT created";
            responseObject = createResponseObject(msg, false);
        }
        return responseObject;
    }

    public ResponseObject removeUser(Long userId){
        ResponseObject responseObject;
        String msg;
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            List<UserResponse> usersResponse = copyPropertiesFromDbToResponse();
            msg = "user with id " + userId + " is deleted successfully";
            responseObject = createResponseObject(msg, true);
            responseObject.setData(usersResponse);
        }else {
            msg = "user with id " + userId + " does not exist";
            responseObject = createResponseObject(msg, false);
        }
        return responseObject;
    }

    public ResponseObject loginUser(UserRequest userRequest){
        ResponseObject responseObject;
        String msg;
        String usernameFromRequest = userRequest.getUsername();
        String passwordFromRequest = userRequest.getPassword();
        if(userRepository.existsByUsername(usernameFromRequest)){
            User userFromDb = userRepository.findByUsername(usernameFromRequest);
            String encryptesPassword = userFromDb.getEncryptedPassword();
            if(bCryptPasswordEncoder.matches(passwordFromRequest,encryptesPassword)){
                UserResponse userResponse =  new UserResponse();
                BeanUtils.copyProperties(userFromDb, userResponse,"encryptedPassword");
                msg = "user with id " + userResponse.getId() + ", is logged successfully";
                responseObject = createResponseObject(msg, true);
                responseObject.getData().add(userResponse);
                return responseObject;
            }
        }
        msg = "username or password are invalid";
        responseObject = createResponseObject(msg, false);
        return responseObject;

    }

    private List<UserResponse> copyPropertiesFromDbToResponse(){
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponse= new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse,"encryptedPassword");
            usersResponse.add(userResponse);
        }
        return usersResponse;
    }

    private ResponseObject createResponseObject(String msg, boolean isSuccess){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(msg);
        responseObject.setSuccess(isSuccess);
        return responseObject;
    }
}
