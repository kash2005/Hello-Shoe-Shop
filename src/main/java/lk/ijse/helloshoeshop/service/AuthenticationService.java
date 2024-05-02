package lk.ijse.helloshoeshop.service;


import lk.ijse.helloshoeshop.repository.request.SignUpRequest;
import lk.ijse.helloshoeshop.repository.request.SigninRequest;
import lk.ijse.helloshoeshop.repository.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);

    JwtAuthenticationResponse refreshToken(String refreshToken);
}
