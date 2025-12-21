package rushikesh.chatappdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rushikesh.chatappdemo.entity.User;
import rushikesh.chatappdemo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getUsers(Authentication auth) {
        return userService.getAllUsersExcept(auth.getName());
    }
}

