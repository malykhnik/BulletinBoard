package com.malykhnik.bulletinboard.controller;


import com.malykhnik.bulletinboard.entity.request.SignInRequest;
import com.malykhnik.bulletinboard.entity.request.SignUpRequest;
import com.malykhnik.bulletinboard.entity.response.JwtAuthenticationResponse;
import com.malykhnik.bulletinboard.exception.UserAlreadyExistsException;
import com.malykhnik.bulletinboard.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) throws UserAlreadyExistsException {
        logger.info("returned refresh token!");
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request) {
        logger.info("returned access token!");
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
