package ite.fifthyear.is.repository;


import ite.fifthyear.is.domain.SavedAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavedAccountRepo extends JpaRepository<SavedAccount, Long> {
    Optional<SavedAccount> findById(Long id);

    SavedAccount findByUsername(String username);
}
