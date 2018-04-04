package com.azarenko;

import com.azarenko.Services.PublicationService;
import com.azarenko.Services.PublicationServiceImpl;
import com.azarenko.Services.UserService;
import com.azarenko.Services.UserServiceImpl;
import com.azarenko.model.Publication;
import com.azarenko.model.Users;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PublicationService publicationService = new PublicationServiceImpl();
        List<Publication> publicationList = publicationService.getCatalog();
        for(Publication pair: publicationList){
            System.out.println(pair.toString());
        }
        UserService userService = new UserServiceImpl();
        List<Users> usersList= userService.getUserList();
        for(Users pair: usersList){
            System.out.println(pair.toString());
        }


    }
}
