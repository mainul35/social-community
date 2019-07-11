package com.mainul35.impl;

import com.mainul35.dao.RoleDao;
import com.mainul35.entity.Role;
import com.mainul35.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Role save(Map<String, String[]> params) {
        Session session = sessionFactory.getCurrentSession();
        Role role = new Role();
        role.setRole("ROLE_USER");
        session.save(role);
        return role;
    }
    
    public Role save(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.save(role);
        return role;
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Role r WHERE r.role = :role";
        Query query = session.createQuery(hql);
        query.setParameter("role", roleName);
        Role role = null;
        if (query.list().size() > 0) {
            role = (Role) query.list().get(0);
        }
        return role;
    }

    @Override
    public void updateRole(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.update(role);
    }
}
