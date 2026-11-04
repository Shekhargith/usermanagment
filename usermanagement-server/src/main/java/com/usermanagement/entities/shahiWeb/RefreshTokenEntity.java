package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import com.usermanagement.entities.shahiWeb.UserEntity;
import jakarta.persistence.*;

@Entity @Table(name = "UM_REFRESH_TOKEN")
public class RefreshTokenEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "REFRESH_ID") private Long refreshId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "USER_ID", nullable = false) private UserEntity user;
    @Column(name = "TOKEN_HASH", nullable = false, unique = true, length = 255) private String tokenHash;
    @Column(name = "EXPIRY_DATE", nullable = false) private LocalDateTime expiryDate;
    @Column(name = "IS_REVOKED") private Integer isRevoked = 0;
    @Column(name = "CREATED_DATE", insertable = false, updatable = false) private LocalDateTime createdDate;
    public RefreshTokenEntity() {}
    public Long getRefreshId(){return refreshId;} public void setRefreshId(Long v){refreshId=v;}
    public UserEntity getUser(){return user;} public void setUser(UserEntity v){user=v;}
    public String getTokenHash(){return tokenHash;} public void setTokenHash(String v){tokenHash=v;}
    public LocalDateTime getExpiryDate(){return expiryDate;} public void setExpiryDate(LocalDateTime v){expiryDate=v;}
    public Integer getIsRevoked(){return isRevoked;} public void setIsRevoked(Integer v){isRevoked=v;}
    public LocalDateTime getCreatedDate(){return createdDate;} public void setCreatedDate(LocalDateTime v){createdDate=v;}
}
