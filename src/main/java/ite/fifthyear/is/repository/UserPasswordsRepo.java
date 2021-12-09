package ite.fifthyear.is.repository;

import ite.fifthyear.is.domain.UserPasswords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPasswordsRepo extends JpaRepository<UserPasswords,Long> {
    Optional<UserPasswords> findById(Long id);
    UserPasswords findByPassword(String password);

}


//TODO check this if correct