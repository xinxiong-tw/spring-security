package demo.spring.security.adapter.controller;

import demo.spring.security.adapter.request.User;
import demo.spring.security.adapter.response.UserResponse;
import demo.spring.security.application.appservice.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService applicationService;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return UserResponse.from(applicationService.findById(id));
    }

    @GetMapping
    public List<UserResponse> findAllUser() {
        throw new AccessDeniedException("wuhu");
//        return applicationService.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @PostMapping("/token")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.username(), user.password()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

            String roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwtClaimsSet.builder()
                    .subject(userDetails.getUsername())
                    .claim("roles", roles)
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(10))
                    .build());
            String token = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
