package com.mainul35.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @NotNull(message = "Location id must not be null")
    private Long id;
    @Column
    @NotNull(message = "Location name must not be empty")
    private String locationName;
    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<User> users;

    // ------------- Mapped with another class
    @JsonIgnore
    @OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
    private List<StatusVisibilityLocation> status_locations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<StatusVisibilityLocation> getVisibilityLocations() {
        return status_locations;
    }

    public void setVisibilityLocations(List<StatusVisibilityLocation> visibilityLocations) {
        this.status_locations = visibilityLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id) &&
                Objects.equals(locationName, location.locationName) &&
                Objects.equals(users, location.users) &&
                Objects.equals(status_locations, location.status_locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locationName, users, status_locations);
    }
}
