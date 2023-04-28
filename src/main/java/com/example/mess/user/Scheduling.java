package com.example.mess.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Scheduling {

    private final UserService userService;

    @Autowired
    public Scheduling(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron= "0 0 0 * * *" )
    public void deleteCount() {
        System.out.println("delete");
        userService.deletefunc();
    }

}
