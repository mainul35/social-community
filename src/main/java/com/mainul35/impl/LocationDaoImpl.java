package com.mainul35.impl;

import com.mainul35.dao.LocationDao;
import com.mainul35.entity.Location;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class LocationDaoImpl implements LocationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Location addLocation(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.save(location);
        return location;
    }

    @Override
    public Location updateLocation(Location location) {
        String hql = "UPDATE Location l set l.locationName = :locationName "  +
        "WHERE l.id = :locationId";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("locationName", location.getLocationName());
        query.setParameter("locationId", location.getId());
        query.executeUpdate();
        return location;
    }

    @Override
    public Location getLocationByName(String locationName) {
        String hql = "FROM Location l WHERE l.locationName = :locationName";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("locationName", locationName);
        Location location = null;
        if (query.list().size() > 0) {
            location = (Location) query.list().get(0);
        }
        return location;
    }

    @Override
    public Location getLocationById(Long id) {
        String hql = "FROM Location l WHERE l.id = :id";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Location location = null;
        if (query.list().size() > 0) {
            location = (Location) query.list().get(0);
        }
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        String hql = "FROM Location l";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        List<Location> locations = (List<Location>)query.list();
        return locations;
    }

}
