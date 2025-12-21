package rushikesh.chatappdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rushikesh.chatappdemo.controller.request.LoginRequest;
import rushikesh.chatappdemo.controller.request.SignupRequest;
import rushikesh.chatappdemo.controller.response.AuthResponse;
import rushikesh.chatappdemo.entity.User;
import rushikesh.chatappdemo.repository.UserRepository;
import rushikesh.chatappdemo.security.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse signup(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        return generateAuthResponse(user.getUsername());
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return generateAuthResponse(user.getUsername());
    }

    private AuthResponse generateAuthResponse(String username) {
        AuthResponse response = new AuthResponse();
        response.setUsername(username);
        response.setToken(jwtUtil.generateToken(username));
        return response;
    }
}

