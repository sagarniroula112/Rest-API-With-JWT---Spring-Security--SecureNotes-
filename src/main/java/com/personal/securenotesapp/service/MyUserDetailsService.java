package com.personal.securenotesapp.service;

import com.personal.securenotesapp.model.User;
import com.personal.securenotesapp.model.UserPrincipal;
import com.personal.securenotesapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if(user != null)
            return new UserPrincipal(user);
        else
            throw new RuntimeException("User not found!");
    }
}
