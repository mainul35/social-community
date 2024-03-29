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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
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

        Role role = roleDao.getRoleByRoleName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setRole("ROLE_USER");
        }
        session.save(role);
        user.setRole(role);
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
        String hql = "UPDATE User u " +
                "set u.username = :username, " +
                "u.email = :email, " +
                "u.password = :password, " +
                "u.name = :name, " +
                "u.updatedOn = :updatedOn," +
                "u.myLocation = :location," +
                "u.role = :role "  +
                "WHERE u.id = :id";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("username", user.getUsername());
        query.setParameter("name", user.getName());
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        query.setParameter("updatedOn", user.getUpdatedOn());
        query.setParameter("location", user.getMyLocation());
        query.setParameter("role", user.getRole());
        query.setParameter("id", user.getId());
        query.executeUpdate();
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
