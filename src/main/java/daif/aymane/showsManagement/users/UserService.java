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
        ResponseObject responseObject = new ResponseObject();
        List<User> users = userRepository.findAll();
        List<UserResponse> usersResponse= new ArrayList<>();
        for (User user: users) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse,"encryptedPassword");
            usersResponse.add(userResponse);
        }
        String msg = users.size() + " users";
        responseObject.setSuccess(true);
        responseObject.setData(usersResponse);

        responseObject.setMessage(msg);
        return responseObject;
    }

    public ResponseObject addUser(UserRequest userRequest){
        ResponseObject responseObject = new ResponseObject();
        User user = new User();
        BeanUtils.copyProperties(userRequest, user, "password");
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        User createdUser = userRepository.save(user);
        String msg;

        if(userRepository.existsById(user.getId())){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(createdUser, userResponse,"encryptedPassword");
            msg = "user with id " + createdUser.getId() + " is created successfully";
            responseObject.setSuccess(true);
            responseObject.getData().add(userResponse);
        }else {
            msg =  "user with id " + createdUser.getId() + " is NOT created";
            responseObject.setSuccess(false);
            responseObject.setData(new ArrayList<>());
        }
        responseObject.setMessage(msg);
        return responseObject;
    }

    public ResponseObject removeUser(Long userId){
        ResponseObject responseObject = new ResponseObject();
        String msg;
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            msg = "user with id " + userId + " is deleted successfully";
            responseObject.setSuccess(true);

            List<User> users = userRepository.findAll();
            List<UserResponse> usersResponse= new ArrayList<>();
            for (User user: users) {
                UserResponse userResponse = new UserResponse();
                BeanUtils.copyProperties(user, userResponse,"encryptedPassword");
                usersResponse.add(userResponse);
            }
            responseObject.setData(usersResponse);
        }else {
            msg = "user with id " + userId + " does not exist";
            responseObject.setSuccess(false);
            responseObject.setData(new ArrayList<>());
        }
        responseObject.setMessage(msg);
        return responseObject;
    }

    public ResponseObject loginUser(UserRequest userRequest){
        ResponseObject responseObject = new ResponseObject();
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
                responseObject.setSuccess(true);
                responseObject.getData().add(userResponse);
            }else {
                msg = "username or password are invalid";
                responseObject.setSuccess(false);
                responseObject.setData(new ArrayList<>());
            }
        }else {
            msg = "username or password are invalid";
            responseObject.setSuccess(false);
            responseObject.setData(new ArrayList<>());
        }
        responseObject.setMessage(msg);
        return responseObject;

    }
}
