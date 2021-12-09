package ite.fifthyear.is.services;



import ite.fifthyear.is.domain.Role;
import ite.fifthyear.is.domain.User;
import ite.fifthyear.is.domain.UserPasswords;
import ite.fifthyear.is.repository.RoleRepo;
import ite.fifthyear.is.repository.UserPasswordsRepo;
import ite.fifthyear.is.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor
@Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final UserPasswordsRepo userPasswordsRepo;
/*
    private final PasswordEncoder passwordEncoder;
*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null){
            log.error("usern not found in the database");
            throw new  UsernameNotFoundException("user not found in the database");
        }else{
            log.info("user found in the database: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("saving new user {} to the database", user.getUsername());
/*
        user.setPassword(passwordEncoder.encode(user.getPassword()));
*/
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new Role {} to the database", role.getName());

        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String RoleName) {
        log.info("adding Role {} to user {}", RoleName, username);

        User user = userRepo.findByUsername((username));
        Role role = roleRepo.findByName(RoleName);
        user.getRoles().add(role);
    }

    @Override
    public void updateUserPassword(String currentPass, String newPass) {
        log.info("updating user password");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername((String) auth.getPrincipal().toString());

        user.setPassword(newPass);
        // add oldpassword to oldpassword table
        saveOldPassword(new UserPasswords(null, currentPass));
        // addoldpasswordtooldpasswordscolumn
        addOldPasswordToUser(user.getUsername(), currentPass);
        // save user
        saveUser(user);
    }

    @Override
    public void addOldPasswordToUser(String username, String oldPass) {
        log.info("adding oldPassword {} to user {}", oldPass, username);

        User user = userRepo.findByUsername((username));
        UserPasswords userPasswords = userPasswordsRepo.findByPassword(oldPass);
        user.getUserPasswords().add(userPasswords);
    }

    @Override
    public User getUser(String username) {
        log.info("fetching user {}", username);

        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("fetching all users");
        return userRepo.findAll();
    }



    //////////////// old passwords ///////////////

    @Override
    public void saveOldPassword(UserPasswords userPasswords) {
        log.info("save current password to old password");
        userPasswordsRepo.save(userPasswords);
    }

    @Override
    public UserPasswords getOldPassword(Long id) {
        log.info("fetching old password by ID {}", id);
        return userPasswordsRepo.getById(id);
    }
}
