package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import com.usermanagement.entities.shahiWeb.UserEntity;
import jakarta.persistence.*;

@Entity @Table(name = "UM_PASSWORD_RESET_TOKEN")
public class PasswordResetTokenEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "RESET_ID") private Long resetId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "USER_ID", nullable = false) private UserEntity user;
    @Column(name = "TOKEN_HASH", nullable = false, unique = true, length = 255) private String tokenHash;
    @Column(name = "EXPIRY_DATE", nullable = false) private LocalDateTime expiryDate;
    @Column(name = "IS_USED") private Integer isUsed = 0;
    @Column(name = "CREATED_DATE", insertable = false, updatable = false) private LocalDateTime createdDate;
    public PasswordResetTokenEntity() {}
    public Long getResetId(){return resetId;} public void setResetId(Long v){resetId=v;}
    public UserEntity getUser(){return user;} public void setUser(UserEntity v){user=v;}
    public String getTokenHash(){return tokenHash;} public void setTokenHash(String v){tokenHash=v;}
    public LocalDateTime getExpiryDate(){return expiryDate;} public void setExpiryDate(LocalDateTime v){expiryDate=v;}
    public Integer getIsUsed(){return isUsed;} public void setIsUsed(Integer v){isUsed=v;}
    public LocalDateTime getCreatedDate(){return createdDate;} public void setCreatedDate(LocalDateTime v){createdDate=v;}
}
