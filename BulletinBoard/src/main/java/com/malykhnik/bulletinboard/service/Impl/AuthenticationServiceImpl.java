package com.malykhnik.bulletinboard.service.Impl;


import com.malykhnik.bulletinboard.entity.request.SignInRequest;
import com.malykhnik.bulletinboard.entity.request.SignUpRequest;
import com.malykhnik.bulletinboard.entity.response.JwtAuthenticationResponse;
import com.malykhnik.bulletinboard.repository.InMemoryUserDB;
import com.malykhnik.bulletinboard.service.AuthenticationService;
import com.malykhnik.bulletinboard.service.JwtService;
import com.malykhnik.bulletinboard.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final InMemoryUserDB userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if (userRepository.findByUsername(request.getUsername()) == null) {
            userRepository.save((User) user);
        }

        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (userRepository.findByUsername(request.getUsername()) == null) {
            throw new UsernameNotFoundException("User not found");
        }
        var user = userRepository.findByUsername(request.getUsername());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

}
