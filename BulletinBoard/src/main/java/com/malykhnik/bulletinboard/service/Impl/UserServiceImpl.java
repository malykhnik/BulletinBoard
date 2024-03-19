package com.malykhnik.bulletinboard.service.Impl;

import com.malykhnik.bulletinboard.repository.InMemoryUserDB;
import com.malykhnik.bulletinboard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final InMemoryUserDB userRepo;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            if (userRepo.findByUsername(username) == null) {
                throw new UsernameNotFoundException("User with " + username + " username not found");
            }
            return userRepo.findByUsername(username);
        };
    }


}