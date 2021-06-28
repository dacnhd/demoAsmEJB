package com.example.demoasmejb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Username is required")
    @Size(min = 2, max = 50, message = "Username from 2 to 50 characters")
    private String username;
    private String passwordHash;
    private int role; //1. Admin - 2. User
    private int status; //1. Active - 0. Deactive

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Credential> tokens;

    public Account(String username, String passwordHash, int role, int status) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.status = status;
    }

    public String getRoleByName() {
        return role == 1 ? "ADMIN" : "USER";
    }
}
