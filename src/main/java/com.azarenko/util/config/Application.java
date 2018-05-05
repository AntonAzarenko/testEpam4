package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "application", namespace = "http://www.azarenko.com")
public class Application {
    @XmlElement(name = "adminPages",required = true)
    private List<AdminPage> adminPageList = new ArrayList<>();
    @XmlElement(name = "userPages",required = true)
    private List<UserPage> userPageList = new ArrayList<>();

   /* public List<AdminPage> getAdminList() {
        return this.adminPageList;
    }

    public List<UserPage> getUserList() {
        return this.userPageList;
    }*/

    public Application() {
    }
    public boolean add(UserPage userPage){
        return userPageList.add(userPage);
    }

    public Application(List<AdminPage> adminPageList, List<UserPage> userPageList) {
        this.adminPageList = adminPageList;
        this.userPageList = userPageList;
    }

    public void setAdminPageList(List<AdminPage> adminPageList) {
        this.adminPageList = adminPageList;
    }

    public void setUserPageList(List<UserPage> userPageList) {
        this.userPageList = userPageList;
    }

    @Override
    public String toString() {
        return "Application{" +
                "adminPageList=" + adminPageList.size() +
                ", userPageList=" + userPageList.size() +
                '}';
    }
}
