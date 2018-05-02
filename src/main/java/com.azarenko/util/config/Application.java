package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "application", namespace = "http://www.azarenko.com")
public class Application {
    @XmlElement(name = "admin",required = true)
    private List<Admin> adminList = new ArrayList<>();
    @XmlElement(name = "user",required = true)
    private List<User> userList = new ArrayList<>();

   /* public List<Admin> getAdminList() {
        return this.adminList;
    }

    public List<User> getUserList() {
        return this.userList;
    }*/

    public Application() {
    }
    public boolean add(User user){
        return userList.add(user);
    }

    public Application(List<Admin> adminList, List<User> userList) {
        this.adminList = adminList;
        this.userList = userList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Application{" +
                "adminList=" + adminList.size() +
                ", userList=" + userList.size() +
                '}';
    }
}
