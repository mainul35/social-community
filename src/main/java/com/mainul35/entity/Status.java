package com.mainul35.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mainul35.enums.Visibility;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @NotNull(message = "ID must not be null")
    private Long id;
    @Column(columnDefinition="TEXT")
    private String status;
    @Column
    private String title;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_writer")
    private User owner;

//---------- 6Mapped List of String location names
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name="visible_to_locations", joinColumns=@JoinColumn(name="status_id"))
//    @Column(name="location_name")
//    private List<String> locations;

// ------------- Mapped with another class
    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<StatusVisibilityLocation> visibilityLocations;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attachments")
    private List<Attachment> attachments;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
    @NotNull(message = "Visibility must have to be selected")
    @Column
    private String visibility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

//    public List<String> getLocations() {
//        return locations;
//    }
//
//    public void setLocations(List<String> locations) {
//        this.locations = locations;
//    }


    public List<StatusVisibilityLocation> getVisibilityLocations() {
        return visibilityLocations;
    }

    public void setVisibilityLocations(List<StatusVisibilityLocation> visibilityLocations) {
        this.visibilityLocations = visibilityLocations;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @NotNull
    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(@NotNull String visibility) {
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status1 = (Status) o;
        return Objects.equals(id, status1.id) &&
                Objects.equals(status, status1.status) &&
                Objects.equals(title, status1.title) &&
                Objects.equals(owner, status1.owner) &&
                Objects.equals(visibilityLocations, status1.visibilityLocations) &&
                Objects.equals(attachments, status1.attachments) &&
                Objects.equals(createdOn, status1.createdOn) &&
                Objects.equals(updatedOn, status1.updatedOn) &&
                Objects.equals(visibility, status1.visibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, title, owner, visibilityLocations, attachments, createdOn, updatedOn, visibility);
    }
}
