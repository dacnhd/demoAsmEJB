package com.example.demoasmejb.entity;

import com.example.demoasmejb.util.DateTimeUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Credential {
    @Id
    private String tokenKey;
    private long createAt; //MLS
    private long expiredAt; //MLS

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "userId")
    private Account account;

    @Column(insertable = false, updatable = false)
    private long userId;

    public boolean isExpired() {
        return this.expiredAt < DateTimeUtil.getCurrenTimeInMLS();
    }
}
