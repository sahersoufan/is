package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.AttachmentFile;

public interface AttachmentFileService {
    void saveNewFile(AttachmentFile attachmentFile);
    void updateFile(AttachmentFile attachmentFile);
    void deleteFile(Long id);
    void deleteFile(String username);
    AttachmentFile getFile(Long id);
    AttachmentFile getFile(String username);
}
