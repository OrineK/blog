package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.dao.RoleDao;
import com.cn.jp.orine.blog.dao.UserDao;
import com.cn.jp.orine.blog.model.Role;
import com.cn.jp.orine.blog.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RoleService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private UserDao userDao;

    public Set<String> findRoleNameByUserId(Long userId) {
        User user = userDao.findUserById(userId);
        List<Role> roles = user.getRoles();
        Set<String> set = new TreeSet<>();
        for (Role role : roles) {
            set.add(role.getRole());
        }
        return set;
    }
}
