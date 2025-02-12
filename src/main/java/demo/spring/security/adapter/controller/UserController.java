package demo.spring.security.adapter.controller;

import demo.spring.security.adapter.request.User;
import demo.spring.security.adapter.response.UserResponse;
import demo.spring.security.application.appservice.UserApplicationService;
import demo.spring.security.infrastructure.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService applicationService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return UserResponse.from(applicationService.findById(id));
    }

    @GetMapping
    public List<UserResponse> findAllUser() {
        return applicationService.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
