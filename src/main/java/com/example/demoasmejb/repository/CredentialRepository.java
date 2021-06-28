package com.example.demoasmejb.repository;

import com.example.demoasmejb.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String> {
    Credential findByUserId(long accountId);

    Optional<Credential> findByTokenKey(String token);
}
