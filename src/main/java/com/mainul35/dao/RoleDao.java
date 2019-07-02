package com.mainul35.dao;

import com.mainul35.entity.Role;
import com.mainul35.entity.User;

import java.util.Map;

public interface RoleDao {
    Role save(Map<String, String[]> params);
    Role save(Role role);
    Role getRoleByRoleName(String roleName);

    void updateRole(Role role);
}
