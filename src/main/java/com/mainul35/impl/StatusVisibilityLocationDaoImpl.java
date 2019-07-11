package com.mainul35.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainul35.dao.StatusVisibilityLocationDao;
import com.mainul35.entity.Location;
import com.mainul35.entity.StatusVisibilityLocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StatusVisibilityLocationDaoImpl implements StatusVisibilityLocationDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void save(StatusVisibilityLocation statusVisibilityLocation) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(statusVisibilityLocation);
	}

	@Override
	public StatusVisibilityLocation getById(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<StatusVisibilityLocation> query = cb.createQuery(StatusVisibilityLocation.class);
		Root<StatusVisibilityLocation> root = query.from(StatusVisibilityLocation.class);
		query.where(cb.equal(root.get("id"), id));
		TypedQuery<StatusVisibilityLocation> typedQuery = session.createQuery(query);
		return typedQuery.getSingleResult();
	}

	@Override
	public List<StatusVisibilityLocation> getStatusListByLocation(Location location) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT svl FROM StatusVisibilityLocation svl ";
        Query query = session.createQuery(hql);
        List<StatusVisibilityLocation> svl = null;
        if (query.list().size() > 0) {
            svl = (List<StatusVisibilityLocation>) query.list();
        }
        return svl;
	}

	@Override
	public StatusVisibilityLocation update(StatusVisibilityLocation statusVisibilityLocation) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        session.update(statusVisibilityLocation);
        return statusVisibilityLocation;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
        String hql = "delete from StatusVisibilityLocation st where st.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
	}
}
