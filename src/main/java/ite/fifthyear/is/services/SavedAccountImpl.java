package ite.fifthyear.is.services;


import ite.fifthyear.is.domain.AttachmentFile;
import ite.fifthyear.is.domain.SavedAccount;
import ite.fifthyear.is.repository.SavedAccountRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SavedAccountImpl implements SavedAccountService {

    private final SavedAccountRepo savedAccountRepo;

    @Override
    public void saveNewAccount(SavedAccount savedAccount) {
        savedAccountRepo.save(savedAccount);
    }

    @Override
    public void updateAccountDetails(SavedAccount savedAccount) {

    }

    @Override
    public void addAttachmentFileToAccount(String AccountName, String fileUrl) {

    }

    @Override
    public void addAttachmentFileToAccount(AttachmentFile attachmentFile) {

    }


    @Override
    public void deleteSavedAccount(Long id) {
        savedAccountRepo.deleteById(id);
    }

    @Override
    public void deleteSavedAccount(String username) {

    }

    @Override
    public SavedAccount getSavedAccount(String username) {
        return null;
    }

    @Override
    public SavedAccount getSavedAccount(Long id) {
        return savedAccountRepo.getById(id);
    }

}
