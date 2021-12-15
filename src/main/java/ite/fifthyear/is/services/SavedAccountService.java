package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.AttachmentFile;
import ite.fifthyear.is.domain.SavedAccount;

import java.util.List;

public interface SavedAccountService {
    void saveNewAccount(SavedAccount savedAccount);
    void updateAccountDetails(SavedAccount savedAccount);
    void addAttachmentFileToAccount(String AccountName, String fileUrl);
    void addAttachmentFileToAccount(AttachmentFile attachmentFile);
    void deleteSavedAccount(Long id);
    void deleteSavedAccount(String AccountName);
    SavedAccount getSavedAccount(String AccountName);
    SavedAccount getSavedAccount(Long id);
}
