package ite.fifthyear.is.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import ite.fifthyear.is.domain.AttachmentFile;
import ite.fifthyear.is.domain.Role;
import ite.fifthyear.is.domain.SavedAccount;
import ite.fifthyear.is.domain.User;
import ite.fifthyear.is.services.SavedAccountService;
import ite.fifthyear.is.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;
    private final SavedAccountService savedAccountService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    //TODO this's for saving user
/*    @PostMapping("/user/save")
    public ResponseEntity<User> saveUserNewTask(@RequestBody UserRegistrationDetail userRegistrationDetail){
        return ResponseEntity.ok().build();
    }*/



    @PostMapping("/user/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/user/update/password")
    public ResponseEntity<?> upadtePassword(@RequestBody currentAndNewPass currentAndNewPass){
        userService.updateUserPassword(currentAndNewPass.getCurrentPass(), currentAndNewPass.getNewPass());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<?> addSavedAcounts(){
        SavedAccount savedAccount = new SavedAccount(
                null,
                "github",
                "sahersoufan",
                "password",
                "desc",
                null
        );
        savedAccountService.saveNewAccount(savedAccount);
        userService.addnewAccount(savedAccount.getUsername());
        return ResponseEntity.ok().build();
    }


}


@Data
class UserRegistrationDetail {
    private String username;
    private String password;
}
@Data
class RoleToUserForm {

    private String  username;
    private String  roleName;
}

@Data
class currentAndNewPass{
    private String currentPass;
    private String newPass;
}

