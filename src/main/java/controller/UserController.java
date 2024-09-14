package main.java.controller;

import main.java.dtos.Transaction;
import main.java.services.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public List<Transaction> settleUser(String userName, String groupName){
        return  userService.settleUser(userName,groupName);
    }
}
