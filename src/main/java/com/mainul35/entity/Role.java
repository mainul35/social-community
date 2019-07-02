package com.mainul35.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String role;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    private User tbl_user;
    @Override
    public String getAuthority() {
        return this.role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return tbl_user;
    }

    public void setUser(User user) {
        this.tbl_user = user;
    }
}
