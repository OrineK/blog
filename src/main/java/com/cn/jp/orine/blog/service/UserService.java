package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.dao.PermissionDao;
import com.cn.jp.orine.blog.dao.RoleDao;
import com.cn.jp.orine.blog.dao.UserDao;
import com.cn.jp.orine.blog.model.Permission;
import com.cn.jp.orine.blog.model.Role;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.utils.RandomUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    @Resource
    private PermissionDao permissionDao;

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

    public int userCount() {
        List<User> users = userDao.findAll();
        return users.size();
    }

    //admin registration
    public User saveInitAdmin(String username, String password) {
        //创建一个权限，不一定用
        Permission permission = new Permission();
        permission.setName("admin:add");
        try {
            permissionDao.save(permission);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.INIT_ADMIN_ERROR);
        }

        // 创建角色
        Role role = new Role();
        role.setRole("admin");
        role.setDescription("管理员");
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        role.setPermissions(permissions);
        try {
            roleDao.save(role);
        } catch (Exception e) {
            throw new BusinessException(ResultMsg.INIT_ADMIN_ERROR);
        }

        User user = new User();
        user.setUsername(username);
        String hashAlgorithmName = "MD5";
        String salt = RandomUtil.generateMixString(10);//以账号作为盐值
        user.setSalt(salt);
        Object crdentials = password;
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        user.setPassword(result.toString());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        //创建用户
        try {
            userDao.save(user);
        }catch (Exception e) {
            throw new BusinessException(ResultMsg.INIT_ADMIN_ERROR);
        }

        return user;
    }
}
