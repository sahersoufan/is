package ite.fifthyear.is.services;

import ite.fifthyear.is.domain.SavedAccount;

import java.util.List;

public interface SavedAccountService {
    void saveNewAccount(SavedAccount savedAccount);
    void updateAccountDetails(SavedAccount savedAccount);
    SavedAccount getSavedAccount(String username);
    SavedAccount getSavedAccount(Long id);
    List<SavedAccount> getSavedAccounts(String username);
    List<SavedAccount> getSavedAccounts(Long id);
}
