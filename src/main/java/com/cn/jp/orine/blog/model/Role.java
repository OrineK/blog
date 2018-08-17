package com.cn.jp.orine.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, length = 50)
    private String role;

    @Setter
    @Getter
    private String description; // 角色描述,UI界面显示使用

    @Setter
    @Getter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "RolePermission", joinColumns = {@JoinColumn(name = "roleId")},
        inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<Permission> permissions;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "roleId")},
        inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

}
