package com.azarenko;

import com.azarenko.dao.PublicationDao;
import com.azarenko.dao.PublicationDaoImpl;
import com.azarenko.dao.UserDao;
import com.azarenko.dao.UserDaoImpl;
import com.azarenko.model.Publication;
import com.azarenko.model.Users;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PublicationDao publicationDao = new PublicationDaoImpl();
        List<Publication> publicationList = publicationDao.getCatalog();
        for(Publication pair: publicationList){
            System.out.println(pair.toString());
        }
        UserDao userDao = new UserDaoImpl();
        List<Users> usersList= userDao.getUserList();
        for(Users pair: usersList){
            System.out.println(pair.toString());
        }


    }
}
