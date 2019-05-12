package com.mainul35.impl;

import com.mainul35.dao.LocationDao;
import com.mainul35.dao.StatusDao;
import com.mainul35.entity.Location;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;
import com.mainul35.enums.Visibility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class StatusDaoImpl implements StatusDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private LocationDao locationDaoImpl;

    @Override
    public Status save(Status status) {
        Session session = sessionFactory.getCurrentSession();
        session.save(status);
        return status;
    }

    @Override
    public Status getById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Status s where s.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Status status = null;
        if (query.list().size() > 0) {
            status = (Status) query.list().get(0);
        }
        return status;
    }

    @Override
    public List<Status> getByOwner(User user) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Status st where st.owner = :user";
        Query query = session.createQuery(hql);
        query.setParameter("user", user);
        List<Status> statusList = null;
        if (query.list().size() > 0) {
            statusList = (List<Status>) query.list();
        }
        return statusList;
    }

    @Override
    public List<Status> getByUserLocation(User user) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Status st";
        Query query = session.createQuery(hql);
        List<Status> statusList = null;
        if (query.list().size() > 0) {
            statusList = (List<Status>)query.list();
            List<Status> finalStatusList = statusList;
            statusList.forEach(e->{
                e.getLocations().forEach(f ->{
                    if(!f.equalsIgnoreCase(user.getMyLocation())) {
                        finalStatusList.remove(e);
                    }
                });
            });
            statusList = finalStatusList;
        }
        return statusList;
    }

    @Override
    public List<Status> getAllPublic() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Status st where st.visibility = :visibility";
        Query query = session.createQuery(hql);
        query.setParameter("visibility", Visibility.valueOf(Visibility.PUBLIC));
        List<Status> statusList = null;
        if (query.list().size() > 0) {
            statusList = (List<Status>) query.list();
        }
        return statusList;
    }

    @Override
    public Status update(Status status) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "UPDATE Status st " +
                "set st.status = :status, " +
                "st.title = :title, " +
//                "st.locations (:locations), " +
                "st.visibility = :visibility, " +
                "st.updatedOn = :updatedOn " +
                "WHERE st.id = :statusId";
        Query query = session.createQuery(hql);
        query.setParameter("status", status.getStatus());
        query.setParameter("title", status.getTitle());
//        query.setParameterList("locations", status.getLocations());
        query.setParameter("visibility", status.getVisibility());
        query.setParameter("updatedOn", status.getUpdatedOn());
        query.setParameter("statusId", status.getId().longValue());
        Integer updated = query.executeUpdate();
        return status;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "delete from Status st where st.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}