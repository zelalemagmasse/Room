package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserClassRepository  extends CrudRepository<UserClass,Long> {

    UserClass findUserClassByUsername(String username);
}
