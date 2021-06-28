package com.example.demoasmejb.service;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.entity.Credential;
import com.example.demoasmejb.repository.AccountRepository;
import com.example.demoasmejb.repository.CredentialRepository;
import com.example.demoasmejb.util.DateTimeUtil;
import com.example.demoasmejb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Account create(Account account){
        account.setPasswordHash(bCryptPasswordEncoder.encode(account.getPasswordHash()));
        return accountRepository.save(account);
    }

    public List<Account> getList(){
        return accountRepository.findAll();
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

    private String token = "";

    public Credential login(String username, String password) {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            if (bCryptPasswordEncoder.matches(password, account.getPasswordHash())){
                Credential credential = new Credential();
                credential.setTokenKey(StringUtil.generateAccessToken());
                credential.setAccount(account);
                credential.setCreateAt(DateTimeUtil.getCurrenTimeInMLS());
                credential.setExpiredAt(DateTimeUtil.getTimeAfterDay(7));

                token = credential.getTokenKey();

                return credentialRepository.save(credential);
            }
        }
        return null;
    }

    public Account getUser(long id) {
        return accountRepository.getById(id);
    }

    public Account profile() {
        if (token.equals("")){
            return null;
        }
        Optional<Credential> optionalCredential = credentialRepository.findById(token);
        Credential credential = optionalCredential.get();
        return credential.getAccount();
    }

    public String logout() {
        token = "";
        return "OK";
    }
}
