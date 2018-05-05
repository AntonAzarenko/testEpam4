package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "application", namespace = "http://www.azarenko.com")
public class Application {
    @XmlElement(name = "adminPages",required = true)
    private List<AdminPages> adminPagesList = new ArrayList<>();
    @XmlElement(name = "userPages",required = true)
    private List<UserPages> userPagesList = new ArrayList<>();

   /* public List<AdminPages> getAdminList() {
        return this.adminPagesList;
    }

    public List<UserPages> getUserList() {
        return this.userPagesList;
    }*/

    public Application() {
    }
    public boolean add(UserPages userPages){
        return userPagesList.add(userPages);
    }

    public Application(List<AdminPages> adminPagesList, List<UserPages> userPagesList) {
        this.adminPagesList = adminPagesList;
        this.userPagesList = userPagesList;
    }

    public void setAdminPagesList(List<AdminPages> adminPagesList) {
        this.adminPagesList = adminPagesList;
    }

    public void setUserPagesList(List<UserPages> userPagesList) {
        this.userPagesList = userPagesList;
    }

    @Override
    public String toString() {
        return "Application{" +
                "adminPagesList=" + adminPagesList.size() +
                ", userPagesList=" + userPagesList.size() +
                '}';
    }
}
