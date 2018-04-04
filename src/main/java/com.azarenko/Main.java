package com.azarenko;

import com.azarenko.model.User;
import com.azarenko.services.PeriodicalsService;
import com.azarenko.services.PeriodicalsServiceImpl;
import com.azarenko.services.UserService;
import com.azarenko.services.UserServiceImpl;
import com.azarenko.model.Periodicals;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PeriodicalsService periodicalsService = new PeriodicalsServiceImpl();
        List<Periodicals> periodicalsList = periodicalsService.getCatalog();
        for(Periodicals pair: periodicalsList){
            System.out.println(pair.toString());
        }
        UserService userService = new UserServiceImpl();
        List<User> userList = userService.getUserList();
        for(User pair: userList){
            System.out.println(pair.toString());
        }


    }
}
