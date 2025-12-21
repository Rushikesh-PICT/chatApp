package rushikesh.chatappdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rushikesh.chatappdemo.entity.User;
import rushikesh.chatappdemo.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsersExcept(String currentUser) {
        return userRepository.findAll()
                .stream()
                .filter(u -> !u.getUsername().equals(currentUser))
                .toList();
    }
}
