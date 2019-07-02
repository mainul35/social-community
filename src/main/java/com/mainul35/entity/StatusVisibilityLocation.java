package com.mainul35.entity;

import javax.persistence.*;

@Entity
@Table(name = "status_locations")
public class StatusVisibilityLocation {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = true)
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
