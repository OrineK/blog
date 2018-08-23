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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "RolePermission", joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "permissionId", referencedColumnName = "id", unique = true)})
    private List<Permission> permissions;

}
