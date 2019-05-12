package com.mainul35.entity;

import com.mainul35.enums.Visibility;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_status")
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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="visible_to_locations", joinColumns=@JoinColumn(name="status_id"))
    @Column(name="location_name")
    private List<String> locations;
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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
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
        if (!(o instanceof Status)) return false;
        Status status1 = (Status) o;
        return Objects.equals(id, status1.id) &&
                Objects.equals(status, status1.status) &&
                Objects.equals(owner, status1.owner) &&
                Objects.equals(locations, status1.locations) &&
                Objects.equals(attachments, status1.attachments) &&
                Objects.equals(createdOn, status1.createdOn) &&
                Objects.equals(updatedOn, status1.updatedOn) &&
                Objects.equals(visibility, status1.visibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, owner, locations, attachments, createdOn, updatedOn, visibility);
    }

}
