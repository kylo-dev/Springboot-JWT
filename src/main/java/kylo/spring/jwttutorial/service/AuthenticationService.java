package kylo.spring.jwttutorial.service;

import kylo.spring.jwttutorial.dto.JwtAuthenticationResponse;
import kylo.spring.jwttutorial.dto.RefreshTokenRequest;
import kylo.spring.jwttutorial.dto.SignUpRequest;
import kylo.spring.jwttutorial.dto.SigninRequest;
import kylo.spring.jwttutorial.entity.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
