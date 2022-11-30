package demo.spring.security.domain.model;

import demo.spring.security.infrastructure.entity.RoleEntity;
import demo.spring.security.infrastructure.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class UserModel {
    private Long id;

    private String name;

    private String username;

    private Set<String> roleNames;

    public static UserModel from(UserEntity user) {
        var roleNames = user.getRoleEntity().stream().map(RoleEntity::getName).collect(Collectors.toSet());
        return new UserModel(user.getId(), user.getName(), user.getUsername(), roleNames);
    }
}
