package wtchrs.dockercomposepractice.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import wtchrs.dockercomposepractice.entity.User;

@Getter
@Setter
@ToString
public class CreateUserRequest {

    private String username;

    public User toUser() {
        return new User(username);
    }
}
