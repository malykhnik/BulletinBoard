package com.malykhnik.bulletinboard.service;


import com.malykhnik.bulletinboard.entity.request.SignInRequest;
import com.malykhnik.bulletinboard.entity.request.SignUpRequest;
import com.malykhnik.bulletinboard.entity.response.JwtAuthenticationResponse;
import com.malykhnik.bulletinboard.exception.UserAlreadyExistsException;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request) throws UserAlreadyExistsException;

    JwtAuthenticationResponse signin(SignInRequest request);
}
