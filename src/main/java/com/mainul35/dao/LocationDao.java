package com.mainul35.dao;

import com.mainul35.entity.Location;

import java.util.List;

public interface LocationDao {

    public Location addLocation(Location location);

    public Location getLocationByName(String locationName);

    public List<Location> getAllLocations();

}
