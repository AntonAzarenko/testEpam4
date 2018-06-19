package com.azarenko.services;


public class AuthorisationService {
    private Md5Hash md5Hash;

    public AuthorisationService() {
        md5Hash = new Md5Hash();
    }

    public String authorizeUser(String login, String password)   {
       return "";
    }
}
