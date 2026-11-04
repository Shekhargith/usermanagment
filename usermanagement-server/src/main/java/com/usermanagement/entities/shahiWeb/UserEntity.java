package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "UM_USERS")
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID") private Long userId;
    @Column(name = "USERNAME", nullable = false, unique = true, length = 50) private String username;
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150) private String email;
    @Column(name = "PASSWORD", nullable = false, length = 255) private String password;
    @Column(name = "MOBILE_NO", length = 15) private String mobileNo;
    @Column(name = "FULL_NAME", nullable = false, length = 150) private String fullName;
    @Column(name = "STATUS", nullable = false, length = 20) private String status = "ACTIVE";
    @Column(name = "EMAIL_VERIFIED", nullable = false) private Integer emailVerified = 0;
    @Column(name = "CREATED_DATE", insertable = false, updatable = false) private LocalDateTime createdDate;
    @Column(name = "UPDATED_DATE") private LocalDateTime updatedDate;
    @Column(name = "CREATED_BY") private Long createdBy;
    @Column(name = "UPDATED_BY") private Long updatedBy;

    public UserEntity() {}
    public Long getUserId(){return userId;} public void setUserId(Long v){userId=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPassword(){return password;} public void setPassword(String v){password=v;}
    public String getMobileNo(){return mobileNo;} public void setMobileNo(String v){mobileNo=v;}
    public String getFullName(){return fullName;} public void setFullName(String v){fullName=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public Integer getEmailVerified(){return emailVerified;} public void setEmailVerified(Integer v){emailVerified=v;}
    public LocalDateTime getCreatedDate(){return createdDate;} public void setCreatedDate(LocalDateTime v){createdDate=v;}
    public LocalDateTime getUpdatedDate(){return updatedDate;} public void setUpdatedDate(LocalDateTime v){updatedDate=v;}
    public Long getCreatedBy(){return createdBy;} public void setCreatedBy(Long v){createdBy=v;}
    public Long getUpdatedBy(){return updatedBy;} public void setUpdatedBy(Long v){updatedBy=v;}
}
