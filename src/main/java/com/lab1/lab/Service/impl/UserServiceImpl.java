package com.lab1.lab.Service.impl;

import com.lab1.lab.Model.User;
import com.lab1.lab.Model.exceptions.*;
import com.lab1.lab.Repository.UserRepository;
import com.lab1.lab.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundExceptionn(userId));
    }
}
