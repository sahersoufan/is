package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.Role;
import ite.fifthyear.is.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String RoleName);
    User getUser(String username);
    List<User> getUsers();
}
