package com.cn.jp.orine.blog.dao;

import com.cn.jp.orine.blog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Role findRoleById(Long id);
}
