package com.eventeasy.EventEasy.services;

import com.eventeasy.EventEasy.models.User;
import com.eventeasy.EventEasy.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Optional<User> getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
