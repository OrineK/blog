package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.dao.RoleDao;
import com.cn.jp.orine.blog.dao.UserDao;
import com.cn.jp.orine.blog.model.Permission;
import com.cn.jp.orine.blog.model.Role;
import com.cn.jp.orine.blog.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findPermissionsByUserId(Long userId) {
        User user = userDao.findUserById(userId);
        List<Permission> permissions = new ArrayList<>();
        Set<String> set = new TreeSet<>();
        for (Role role : user.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                set.add(permission.getName());
            }
        }
        return set;
    }

    public void saveUser(User user) {
        userDao.save(user);
    }
}
