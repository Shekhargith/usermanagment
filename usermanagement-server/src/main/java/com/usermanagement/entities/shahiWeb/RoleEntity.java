package com.usermanagement.entities.shahiWeb;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "UM_ROLES")
public class RoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID") private Long roleId;
    @Column(name = "ROLE_NAME", nullable = false, unique = true, length = 50) private String roleName;
    @Column(name = "DESCRIPTION", length = 200) private String description;
    @Column(name = "STATUS", length = 20) private String status = "ACTIVE";
    @Column(name = "CREATED_DATE", insertable = false, updatable = false) private LocalDateTime createdDate;
    public RoleEntity() {}
    public Long getRoleId(){return roleId;} public void setRoleId(Long v){roleId=v;}
    public String getRoleName(){return roleName;} public void setRoleName(String v){roleName=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDateTime getCreatedDate(){return createdDate;} public void setCreatedDate(LocalDateTime v){createdDate=v;}
}
