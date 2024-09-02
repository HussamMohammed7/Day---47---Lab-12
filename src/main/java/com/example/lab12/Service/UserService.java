package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void register(User user){
        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        userRepository.save(user);

    }

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }
    public void updateUser(Integer id, User user) {
        User updatedUser = userRepository.findUserById(id);

        if (updatedUser == null){
            throw new ApiException("User not found");
        }

        updatedUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        updatedUser.setPassword(hash);
        userRepository.save(updatedUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUserById(id);

        if (user == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }
}
