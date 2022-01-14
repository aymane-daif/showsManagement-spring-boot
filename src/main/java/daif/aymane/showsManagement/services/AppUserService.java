package daif.aymane.showsManagement.services;

import daif.aymane.showsManagement.models.AppUser;
import daif.aymane.showsManagement.models.UserRole;
import daif.aymane.showsManagement.repositories.AppUserRepository;
import daif.aymane.showsManagement.repositories.UserRoleRepository;
import daif.aymane.showsManagement.dto.users.ResponseObject;
import daif.aymane.showsManagement.dto.users.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public ResponseObject addUser(AppUser appUser){
        ResponseObject responseObject;
        String msg;
        if(appUserRepository.findByUsername(appUser.getUsername()) != null){
            msg =  "user already exists";
            responseObject = createResponseObject(msg, false);
        }else {
            appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            appUser.getUserRoles().add(userRoleRepository.findByRoleName("USER"));
            AppUser createdAppUser = appUserRepository.save(appUser);

            if(appUserRepository.existsById(appUser.getUserId())){
                UserResponse userResponse = new UserResponse();
                BeanUtils.copyProperties(createdAppUser, userResponse,"password");
                userResponse.setTotalTvShows(createdAppUser.getTVShows().size());
                msg = "user with id " + createdAppUser.getUserId() + " is created successfully";
                responseObject = createResponseObject(msg, true);
                responseObject.getData().add(userResponse);

            }else {
                msg =  "user with id " + createdAppUser.getUserId() + " is NOT created";
                responseObject = createResponseObject(msg, false);
            }
        }
        return responseObject;
    }

    public ResponseObject removeUser(String username){
        ResponseObject responseObject;
        String msg;
        if(appUserRepository.existsByUsername(username)){
            appUserRepository.deleteByUsername(username);
            List<UserResponse> usersResponse = copyPropertiesFromDbToResponse();
            msg = "user is deleted successfully";
            responseObject = createResponseObject(msg, true);
            responseObject.setData(usersResponse);
        }else {
            msg = "user does not exist";
            responseObject = createResponseObject(msg, false);
        }
        return responseObject;
    }

    public ResponseObject getUser(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if (username.equals(currentUserName)){
                ResponseObject responseObject;
                String msg;
                if(appUserRepository.existsByUsername(username)){
                    AppUser fetchedUser = appUserRepository.findByUsername(username);
                    UserResponse userResponse = new UserResponse();
                    BeanUtils.copyProperties(fetchedUser, userResponse,"encryptedPassword");
                    msg = "user is fetched successfully";
                    responseObject = createResponseObject(msg, true);
                    responseObject.getData().add(userResponse);
                }else {
                    msg = "user does not exist";
                    responseObject = createResponseObject(msg, false);
                }
                return responseObject;
            }
        }
        throw new IllegalStateException("unauthorized");
    }

    public ResponseObject loginUser(AppUser appUser){
        ResponseObject responseObject;
        String msg;
        String usernameFromRequest = appUser.getUsername();
        String passwordFromRequest = appUser.getPassword();
        if(appUserRepository.existsByUsername(usernameFromRequest)){
            AppUser appUserFromDb = appUserRepository.findByUsername(usernameFromRequest);
            String encryptedPassword = appUserFromDb.getPassword();
            if(bCryptPasswordEncoder.matches(passwordFromRequest,encryptedPassword)){
                UserResponse userResponse =  new UserResponse();
                BeanUtils.copyProperties(appUserFromDb, userResponse,"password");
                msg = "user is logged successfully";
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
            return new User(appUser.getUsername(), appUser.getPassword(),authorities);
        }else throw new UsernameNotFoundException(username);

    }
}
