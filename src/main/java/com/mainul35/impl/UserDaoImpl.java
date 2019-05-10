package com.mainul35.impl;

import com.mainul35.dao.UserDao;
import com.mainul35.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Properties;

@Component
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    Properties jdbcProperties;

    @Override
    @Transactional
    public List<User> list() {
        String hql = "FROM User";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<User> listUser = (List<User>) query.list();
        return listUser;
    }

    /* Method to CREATE an employee in the database */
    public User addUser(String name){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        User user = new User();
        try {
            tx = session.beginTransaction();
            user.setUsername(name);
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
