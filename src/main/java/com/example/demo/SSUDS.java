package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUDS implements UserDetailsService {

    private UserClassRepository userRepository;

    public SSUDS(UserClassRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserClass user = userRepository.findUserClassByUsername(username);
            if(user==null)
            {
                throw new UsernameNotFoundException(username+" not found");
            }
            System.out.println(user.getUsername()+" is granted access");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthorities(user));


        }catch (Exception e)
        {
            throw new UsernameNotFoundException("User not found");

        }

    }

    private Set<GrantedAuthority> getAuthorities(UserClass user)
    {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(UserRole role: user.getRoles())
        {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
            System.out.println(grantedAuthority.toString());
        }

        return authorities;
    }


}