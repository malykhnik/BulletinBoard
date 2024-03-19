package com.malykhnik.bulletinboard.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
@RequiredArgsConstructor
public class InMemoryUserDB {
    Set<User> authors = new HashSet<>();

    public User findByUsername(String username) {
        return authors.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public void save(User user) {
        authors.add(user);
    }

}
