package com.task_manager.controller;

import com.task_manager.model.Car;
import com.task_manager.model.Task;
import com.task_manager.model.User;
import com.task_manager.service.UserService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Oznacza, że klasa ta jest kontrolerem REST API (JSON, XML, itp.), return JSON
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Wstrzykiwanie zależności
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    public User addUser(@RequestBody User user) { // RequestBody przyjmuje wartości w formacie JSON
        return userService.addUser(user);
    }

    @GetMapping("/panel")
    @ResponseBody
    public User getUserData() {
        return userService.getUserData();
    }

    @GetMapping("/cars/{userId}")
    @ResponseBody
    public List<Car> getUsersCars(@PathVariable int userId){
        return userService.getUserCars(userId);
    }
}