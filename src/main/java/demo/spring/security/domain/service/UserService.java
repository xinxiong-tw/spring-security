package demo.spring.security.domain.service;

import demo.spring.security.domain.model.UserModel;
import demo.spring.security.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserModel findById(Long id) {
        return userRepository.findById(id).map(UserModel::from).orElse(null);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll().stream().map(UserModel::from).collect(Collectors.toList());
    }
}
