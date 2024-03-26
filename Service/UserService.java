package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        if(userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");

        userRepository.save(user);
    }

    public void updateUser(User updatedUser, Integer id){
        User user = getUserById(id);

        if(!user.getEmail().equals(updatedEmail) && userRepository.existsByEmail(updatedEmail))
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        user.setAge(updatedUser.getAge());
        user.setRole(updatedUser.getRole());

        userRepository.save(user);
    }

    public void deleteUser(Integer id){
        User user = getUserById(id);
        userRepository.delete(user);
    }

    public User getUserById(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

}
