package demo.spring.security.adapter.controller;

import demo.spring.security.adapter.reponse.UserReponse;
import demo.spring.security.application.appservice.UserApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserApplicationService applicationService;

    @GetMapping("/{id}")
    public UserReponse getUserById(@PathVariable("id") Long id) {
        return UserReponse.from(applicationService.findById(id));
    }

    @GetMapping
    public List<UserReponse> findAllUser() {
        return applicationService.findAll().stream().map(UserReponse::from).collect(Collectors.toList());
    }
}
