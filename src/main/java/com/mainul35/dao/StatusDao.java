package com.mainul35.dao;

import com.mainul35.entity.Location;
import com.mainul35.entity.Status;
import com.mainul35.entity.User;

import java.util.List;

public interface StatusDao {

    public Status save(Status status);

    public Status getById(Long id);

    public List<Status> getByOwner(User user);

    public List<Status> getByUserLocation(User user);

    public List<Status> getAllPublic();

    public Status update(Status status);

    public void delete(Long id);
}
