package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import com.usermanagement.entities.shahiWeb.RoleEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "UM_USER_ROLES", uniqueConstraints = @UniqueConstraint(name = "UM_UK_USER_ROLE", columnNames = {"USER_ID", "ROLE_ID"}))
public class UserRoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ROLE_ID") private Long userRoleId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "USER_ID", nullable = false) private UserEntity user;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "ROLE_ID", nullable = false) private RoleEntity role;
    @Column(name = "ASSIGNED_DATE", insertable = false, updatable = false) private LocalDateTime assignedDate;
    @Column(name = "ASSIGNED_BY") private Long assignedBy;
    public UserRoleEntity() {}
    public Long getUserRoleId(){return userRoleId;} public void setUserRoleId(Long v){userRoleId=v;}
    public UserEntity getUser(){return user;} public void setUser(UserEntity v){user=v;}
    public RoleEntity getRole(){return role;} public void setRole(RoleEntity v){role=v;}
    public LocalDateTime getAssignedDate(){return assignedDate;} public void setAssignedDate(LocalDateTime v){assignedDate=v;}
    public Long getAssignedBy(){return assignedBy;} public void setAssignedBy(Long v){assignedBy=v;}
}
