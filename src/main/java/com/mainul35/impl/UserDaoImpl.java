package com.mainul35.impl;

import com.mainul35.dao.RoleDao;
import com.mainul35.dao.UserDao;
import com.mainul35.entity.Role;
import com.mainul35.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        String hql = "FROM User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<User> listUser = (List<User>) query.list();
        return listUser;
    }

    public User addUser(User user){
        Session session = sessionFactory.getCurrentSession();
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedOn(new Date());
        user.setUpdatedOn(new Date());

        Role role = null;
        if (role == null) {
            role = new Role();
            role.setRole("ROLE_USER");
            session.save(role);
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        session.save(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        User user = null;
        if (query.list().size() > 0) {
            user = (User) query.list().get(0);
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        User user = null;
        if (query.list().size() > 0) {
            user = (User) query.list().get(0);
        }
        return user;
    }

    @Override
    public User update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;

    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM User u WHERE u.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", email);
        User user = null;
        if (query.list().size() > 0) {
            user = (User) query.list().get(0);
        }
        return user;
    }
}
