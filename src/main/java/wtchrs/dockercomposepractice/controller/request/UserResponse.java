package wtchrs.dockercomposepractice.controller.request;

import lombok.Getter;
import lombok.Setter;
import wtchrs.dockercomposepractice.entity.User;

@Getter
@Setter
public class UserResponse {

    private Long userId;
    private String username;

    public UserResponse(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
