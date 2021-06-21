package com.example.demoasmejb.config;

import com.example.demoasmejb.entity.Account;
import com.example.demoasmejb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (optionalAccount.isPresent()){
            Account account = optionalAccount.get();

            UserDetails userDetails = User.builder()
                    .username(account.getUsername())
                    .password(account.getPasswordHash())
                    .roles(account.getRoleByName()) // lay tu bang role, co the co quan he
                    .build();
            return userDetails;
        }
        return null;
    }
}
