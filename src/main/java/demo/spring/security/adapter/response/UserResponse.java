package demo.spring.security.adapter.response;

import demo.spring.security.domain.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserResponse {
    private Long id;

    private String name;

    private String username;
    private Set<String> roleNames;

    public static UserResponse from(UserModel userModel) {
        return new UserResponse(userModel.getId(), userModel.getName(), userModel.getUsername(), userModel.getRoleNames());
    }
}
