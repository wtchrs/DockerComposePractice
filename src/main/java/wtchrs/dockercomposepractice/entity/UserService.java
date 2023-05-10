package wtchrs.dockercomposepractice.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalStateException("Already exists username");
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new IllegalStateException("Not exists user id");
        userRepository.deleteById(id);
    }
}
