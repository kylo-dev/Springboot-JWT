package kylo.spring.jwttutorial.service.impl;

import kylo.spring.jwttutorial.config.auth.PrincipalDetails;
import kylo.spring.jwttutorial.dto.JwtAuthenticationResponse;
import kylo.spring.jwttutorial.dto.RefreshTokenRequest;
import kylo.spring.jwttutorial.dto.SignUpRequest;
import kylo.spring.jwttutorial.dto.SigninRequest;
import kylo.spring.jwttutorial.entity.Role;
import kylo.spring.jwttutorial.entity.User;
import kylo.spring.jwttutorial.repository.UserRepository;
import kylo.spring.jwttutorial.service.AuthenticationService;
import kylo.spring.jwttutorial.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Transactional
    public User signup(SignUpRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signinRequest.getEmail(), signinRequest.getPassword()));

        User user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        PrincipalDetails principalDetails = new PrincipalDetails(user);

        String token = jwtService.generateToken(principalDetails);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), principalDetails);

        return JwtAuthenticationResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractEmail(refreshTokenRequest.getToken());

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));

        PrincipalDetails principalDetails = new PrincipalDetails(user);

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), principalDetails)){
            String newToken = jwtService.generateToken(principalDetails);

            return JwtAuthenticationResponse.builder()
                    .token(newToken)
                    .refreshToken(refreshTokenRequest.getToken())
                    .build();
        }
        return null;
    }
}
