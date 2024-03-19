package com.malykhnik.bulletinboard.service;


import com.malykhnik.bulletinboard.entity.request.SignInRequest;
import com.malykhnik.bulletinboard.entity.request.SignUpRequest;
import com.malykhnik.bulletinboard.entity.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
