package com.example.demo;


import javax.persistence.*;
import java.util.List;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserClass> getUsers() {
        return users;
    }

    public void setUsers(List<UserClass> users) {
        this.users = users;
    }

    @Column(unique = true)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<UserClass> users;

}