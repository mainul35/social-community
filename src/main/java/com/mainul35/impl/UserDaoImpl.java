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

    public User addUser(Map<String, String[]> params){
        Session session = sessionFactory.getCurrentSession();
        User user = new User();
        user.setName(params.get("name")[0]);
        user.setEmail(params.get("email")[0]);
        user.setUsername(params.get("email")[0].split("@")[0]);
        user.setPassword(passwordEncoder.encode(params.get("password")[0]));
        user.setCreatedOn(new Date());
        user.setUpdatedOn(new Date());

        Role role = roleDao.getRoleByRoleName("ROLE_USER");
        if (role == null) {
            role = new Role();
            role.setRole("ROLE_USER");
            session.save(role);
        }
        user.getRoles().add(role);
        session.save(user);
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
