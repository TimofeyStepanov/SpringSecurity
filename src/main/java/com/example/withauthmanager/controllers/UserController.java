package com.example.withauthmanager.controllers;

import com.example.withauthmanager.models.UserRequest;
import com.example.withauthmanager.models.UserResponse;
import com.example.withauthmanager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    @Operation(summary = "Регистрация")
    public UserRequest register(@RequestBody UserRequest user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация")
    public String login(@RequestBody UserRequest user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/info")
    @Operation(summary = "Информация о пользователе")
    public UserResponse getRole(@RequestParam String userEmail) {
        return userService.getUserRole(userEmail);
    }
}
