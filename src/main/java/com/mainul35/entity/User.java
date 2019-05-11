package com.mainul35.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {
    @Id
    @NotNull(message = "ID must not be null")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @NotNull(message = "Password is required")
    @Size(min=6, max=70, message = "Password must be between 6-70 characters")
    @Column
    private String password;
    @NotNull(message = "Name is required")
    @Size(min=2, max=70, message = "Name must be between 2-70 characters")
    @Column
    private String name;
    @NotNull(message = "Email must not be empty")
    @Size(min=5, max=60, message = "Email must be between 5-60 characters")
    @Email(message = "Invalid email! Please enter valid email")
    @Column
    private String email;
    @Column
    private Date createdOn;
    @Column
    private Date updatedOn;
    @Column
    private boolean enabled = true;
    @OneToMany(orphanRemoval=true, fetch=FetchType.EAGER)
    @JoinTable(name = "user_roles")
    List<Role> roles = new ArrayList<Role>();
    @OneToOne
    private Location myLocation;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId () {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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

    public List<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
