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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String role;

    private String description; // 角色描述,UI界面显示使用

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission", joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "permissionId", referencedColumnName = "id", unique = true)})
    private List<Permission> permissions;

}
