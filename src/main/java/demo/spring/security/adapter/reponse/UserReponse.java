package demo.spring.security.adapter.reponse;

import demo.spring.security.domain.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserReponse {
    private Long id;

    private String name;

    private String username;
    private Set<String> roleNames;

    public static UserReponse from(UserModel userModel) {
        return new UserReponse(userModel.getId(), userModel.getName(), userModel.getUsername(), userModel.getRoleNames());
    }
}
