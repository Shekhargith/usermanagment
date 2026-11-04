package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import com.usermanagement.entities.shahiWeb.UserEntity;
import jakarta.persistence.*;

@Entity @Table(name = "UM_LOGIN_HISTORY")
public class LoginHistoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "HISTORY_ID") private Long historyId;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "USER_ID") private UserEntity user;
    @Column(name = "USERNAME", length = 50) private String username;
    @Column(name = "IP_ADDRESS", length = 50) private String ipAddress;
    @Column(name = "LOGIN_STATUS", length = 20) private String loginStatus;
    @Column(name = "FAILURE_REASON", length = 200) private String failureReason;
    @Column(name = "LOGIN_DATE", insertable = false, updatable = false) private LocalDateTime loginDate;
    public LoginHistoryEntity() {}
    public Long getHistoryId(){return historyId;} public void setHistoryId(Long v){historyId=v;}
    public UserEntity getUser(){return user;} public void setUser(UserEntity v){user=v;}
    public String getUsername(){return username;} public void setUsername(String v){username=v;}
    public String getIpAddress(){return ipAddress;} public void setIpAddress(String v){ipAddress=v;}
    public String getLoginStatus(){return loginStatus;} public void setLoginStatus(String v){loginStatus=v;}
    public String getFailureReason(){return failureReason;} public void setFailureReason(String v){failureReason=v;}
    public LocalDateTime getLoginDate(){return loginDate;} public void setLoginDate(LocalDateTime v){loginDate=v;}
}
