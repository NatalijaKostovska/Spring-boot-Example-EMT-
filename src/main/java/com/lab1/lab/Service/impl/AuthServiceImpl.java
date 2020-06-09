package com.lab1.lab.Service.impl;

import com.lab1.lab.Model.User;
import com.lab1.lab.Repository.UserRepository;
import com.lab1.lab.Service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
//        User user =  this.userRepository.getOne("emt-user");
//        if (user == null) {
//            user = new User();
//            user.setUsername("emt-user");
//            return this.userRepository.save(user);
//        }

        return this.userRepository.findById("emt-user")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("emt-user");
                    return this.userRepository.save(user);
                });

    }

    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }
}
