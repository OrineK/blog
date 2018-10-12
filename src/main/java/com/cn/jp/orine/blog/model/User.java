package com.cn.jp.orine.blog.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    private String salt;  //加密的盐

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(name = "userId")},
        inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<Role> roles;

    private String email;

    private int active = 0;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String loginIpAddress;

    private String createIpAddress = "0.0.0.1";

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public enum Gender {
        MALE("女"),
        FAMALE("男");

        private String text;

        Gender(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
