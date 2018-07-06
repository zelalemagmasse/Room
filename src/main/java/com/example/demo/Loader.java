package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Loader  implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public  void run(String ... Strings)throws Exception{
        UserRole r = new UserRole();
        r.setRole("USER");
        roleRepository.save(r);

        r = new UserRole();
        r.setRole("DAVE");
        roleRepository.save(r);
    }
}
