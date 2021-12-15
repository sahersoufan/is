package ite.fifthyear.is.repository;

import ite.fifthyear.is.domain.AttachmentFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttachmentFileRepo extends JpaRepository<AttachmentFile, Long> {
    Optional<AttachmentFile> findById(Long id);

    AttachmentFile findByFileUri(String fileUri);
}
