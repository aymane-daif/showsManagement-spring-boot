package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.models.AppUser;
import daif.aymane.showsManagement.models.UserRole;
import daif.aymane.showsManagement.repositories.AppUserRepository;
import daif.aymane.showsManagement.repositories.UserRoleRepository;
import daif.aymane.showsManagement.dto.users.ResponseObject;
import daif.aymane.showsManagement.dto.users.UserRequest;
import daif.aymane.showsManagement.dto.users.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AppUserService(AppUserRepository appUserRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void saveRole(UserRole userRole){
        userRoleRepository.save(userRole);
    }

    public void addRoleToUser(String username, String role){
        AppUser appUser = appUserRepository.findByUsername(username);
        UserRole userRole = userRoleRepository.findByRoleName(role);
        appUser.getUserRoles().add(userRole);
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
        AppUser appUser = new AppUser();
        String msg;

        BeanUtils.copyProperties(userRequest, appUser, "password");
        appUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        appUser.getUserRoles().add(userRoleRepository.findByRoleName("USER"));
        AppUser createdAppUser = appUserRepository.save(appUser);

        if(appUserRepository.existsById(appUser.getUserId())){
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(createdAppUser, userResponse,"encryptedPassword");
            msg = "user with id " + createdAppUser.getUserId() + " is created successfully";
            responseObject = createResponseObject(msg, true);
            responseObject.getData().add(userResponse);

        }else {
            msg =  "user with id " + createdAppUser.getUserId() + " is NOT created";
            responseObject = createResponseObject(msg, false);
        }
        return responseObject;
    }

    public ResponseObject removeUser(Long userId){
        ResponseObject responseObject;
        String msg;
        if(appUserRepository.existsById(userId)){
            appUserRepository.deleteById(userId);
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

    public ResponseObject getUser(Long userId){
        ResponseObject responseObject;
        String msg;
        if(appUserRepository.existsById(userId)){
            AppUser fetchedUser = appUserRepository.findById(userId).get();
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(fetchedUser, userResponse,"encryptedPassword");
            msg = "user with id " + userId + " is fetched successfully";
            responseObject = createResponseObject(msg, true);
            responseObject.getData().add(userResponse);
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
        if(appUserRepository.existsByUsername(usernameFromRequest)){
            AppUser appUserFromDb = appUserRepository.findByUsername(usernameFromRequest);
            String encryptesPassword = appUserFromDb.getEncryptedPassword();
            if(bCryptPasswordEncoder.matches(passwordFromRequest,encryptesPassword)){
                UserResponse userResponse =  new UserResponse();
                BeanUtils.copyProperties(appUserFromDb, userResponse,"encryptedPassword");
                msg = "user with id " + userResponse.getUserId() + ", is logged successfully";
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
        List<AppUser> appUsers = appUserRepository.findAll();
        List<UserResponse> usersResponse= new ArrayList<>();
        for (AppUser appUser : appUsers) {
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(appUser, userResponse,"encryptedPassword");
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser != null) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            appUser.getUserRoles().forEach(role ->{
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            });
            return new User(appUser.getUsername(), appUser.getEncryptedPassword(),authorities);
        }else throw new UsernameNotFoundException(username);

    }
}
