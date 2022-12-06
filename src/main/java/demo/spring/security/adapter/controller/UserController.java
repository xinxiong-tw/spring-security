package demo.spring.security.adapter.controller;

import demo.spring.security.adapter.response.UserResponse;
import demo.spring.security.application.appservice.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService applicationService;

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        return UserResponse.from(applicationService.findById(id));
    }

    @GetMapping
    public List<UserResponse> findAllUser() {
        return applicationService.findAll().stream().map(UserResponse::from).collect(Collectors.toList());
    }
}
