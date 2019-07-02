package com.mainul35.dao;

import java.util.List;

import com.mainul35.entity.Location;
import com.mainul35.entity.StatusVisibilityLocation;

public interface StatusVisibilityLocationDao {
	
    public void save(StatusVisibilityLocation statusVisibilityLocation);

    public StatusVisibilityLocation getById(Long id);

    public List<StatusVisibilityLocation> getStatusListByLocation(Location location);
    
    public StatusVisibilityLocation update(StatusVisibilityLocation statusVisibilityLocation);

    public void delete(Long id);
}
