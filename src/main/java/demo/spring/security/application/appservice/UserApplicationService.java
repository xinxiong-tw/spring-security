package demo.spring.security.application.appservice;

import demo.spring.security.adapter.reponse.UserReponse;
import demo.spring.security.domain.model.UserModel;
import demo.spring.security.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserService userService;
    public UserModel findById(Long id) {
        return userService.findById(id);
    }

    public List<UserModel> findAll() {
        return userService.findAll();
    }
}
