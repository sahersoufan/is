package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.Role;
import ite.fifthyear.is.domain.User;
import ite.fifthyear.is.domain.UserPasswords;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String RoleName);
    void updateUserPassword(String currentPass, String newPass);
    void addOldPasswordToUser(String username, String oldPass);
    User getUser(String username);
    List<User> getUsers();
    void saveOldPassword(UserPasswords userPasswords);
    UserPasswords getOldPassword(Long id);
}
