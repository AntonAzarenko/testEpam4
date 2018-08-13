package com.azarenko.services.logic;


import com.azarenko.model.User;
import com.azarenko.services.Md5Hash;
import com.azarenko.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorisationService {
    private Md5Hash md5Hash;

    @Autowired
    UserService service;

    public AuthorisationService() {
        md5Hash = new Md5Hash();
    }

    public boolean authorizeUser(String login, String password)   {
        User user = service.getByEmail(login);
        if(user != null){
              if(user.getPassword().equals(password)){
                  return true;
              }
        }

       return false;
    }
}
