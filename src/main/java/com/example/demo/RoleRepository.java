package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole,Long> {

    UserRole findByRole(String rolename);
}
