package com.example.demo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserClass {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long id;
    private String email;
    private String password;
    private String username;
    @ManyToMany(fetch= FetchType.EAGER)
    private Set<UserRole> roles;

    public UserClass() {
        roles=new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }



    public void  addRole(UserRole rr){
        this.roles.add(rr);
    }


}
