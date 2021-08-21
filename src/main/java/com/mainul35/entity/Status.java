package com.mainul35.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "graph.statusAuthoredByAndVisibilityLocationsAndAttachments",
                attributeNodes = {
                        @NamedAttributeNode(value = "owner"),
                        @NamedAttributeNode(value = "locations"),
                }
        )
})
@Entity
@Table(name = "tbl_status")
public class Status {
    @Id
    @NotNull(message = "ID must not be null")
    @Column(name = "status_id")
    private Long statusId;
    @Column(columnDefinition="TEXT")
    private String status;
    @Column
    private String title;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_writer")
    private User owner;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "status_locations", joinColumns = {
            @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "location_id", referencedColumnName = "id")
    })
    private List<Location> locations;
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "attachments")
//    private List<Attachment> attachments;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
    @NotNull(message = "Visibility must have to be selected")
    @Column
    private String visibility;

    public Long getId() {
        return statusId;
    }

    public void setId(Long id) {
        this.statusId = id;
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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

//    public List<Attachment> getAttachments() {
//        return attachments;
//    }
//
//    public void setAttachments(List<Attachment> attachments) {
//        this.attachments = attachments;
//    }

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
        if (!(o instanceof Status)) return false;
        Status status1 = (Status) o;
        return Objects.equals(statusId, status1.statusId) &&
                Objects.equals(status, status1.status) &&
                Objects.equals(owner, status1.owner) &&
                Objects.equals(locations, status1.locations) &&
//                Objects.equals(attachments, status1.attachments) &&
                Objects.equals(createdOn, status1.createdOn) &&
                Objects.equals(updatedOn, status1.updatedOn) &&
                Objects.equals(visibility, status1.visibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status, owner, locations, createdOn, updatedOn, visibility);
    }

}
