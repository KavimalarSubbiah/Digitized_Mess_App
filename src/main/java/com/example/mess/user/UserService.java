package com.example.mess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deletefunc() {
        Iterable<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            user.setBfCount(0);
            user.setLunchCount(0);
            user.setSnacksCount(0);
            user.setDinnerCount(0);
            userRepository.save(user);
        }
    }

}

