package com.task_manager.service;

import com.task_manager.model.Task;
import com.task_manager.model.User;
import com.task_manager.model.Car;
import com.task_manager.repository.UserRepository;
import com.task_manager.repository.CarRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, CarRepository carRepository)
    {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    public User addUser(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); //Szyfrowanie hasła
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public void addCarToUser(@RequestBody User user, @RequestParam Car car){
        user.addCar(car);
    }
    public List<Car> getUserCars(@RequestParam int userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getCars();
    }
}
