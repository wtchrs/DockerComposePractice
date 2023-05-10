package wtchrs.dockercomposepractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import wtchrs.dockercomposepractice.controller.request.CreateUserRequest;
import wtchrs.dockercomposepractice.controller.request.UserResponse;
import wtchrs.dockercomposepractice.entity.User;
import wtchrs.dockercomposepractice.entity.UserRepository;
import wtchrs.dockercomposepractice.entity.UserService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/users/{userId}")
    public UserResponse userDetail(@PathVariable Long userId) {
        Optional<User> findUser = userRepository.findById(userId);
        if (findUser.isEmpty()) throw new IllegalStateException("Not exists user id");
        return UserResponse.from(findUser.get());
    }

    @GetMapping("/users")
    public Page<UserResponse> userList(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponse::from);
    }

    @PostMapping("/users/new")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {
        return UserResponse.from(userService.createUser(request.toUser()));
    }

    @DeleteMapping("/users/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "Deleted";
    }
}
