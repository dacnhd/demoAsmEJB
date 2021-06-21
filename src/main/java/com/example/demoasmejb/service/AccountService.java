package com.example.demoasmejb.service;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Credential;
import com.example.demoasmejb.repository.AccountRepository;
import com.example.demoasmejb.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account create(Account account){
        account.setPasswordHash(passwordEncoder.encode(account.getPasswordHash()));
        return accountRepository.save(account);
    }
    public Account findAccountByToken(String accessToken) {
        Optional<Credential> optionalCredential = credentialRepository.findById(accessToken);
        if (optionalCredential.isPresent()){
            Credential credential = optionalCredential.get();
            if (credential.isExpired()){
                return null;
            }
            return credential.getAccount();
        }
        return null;
    }
}
