package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "application")
public class Application {
    @XmlElementWrapper(name = "adminPages")
    @XmlElement(name = "page",required = true)
    private List<AdminPage> adminPageList = new ArrayList<>();
    @XmlElementWrapper(name = "userPages")
    @XmlElement(name = "page",required = true)
    private List<UserPage> userPageList = new ArrayList<>();

    public List<AdminPage> getAdminList() {
        return this.adminPageList;
    }

    public List<UserPage> getUserList() {
        return this.userPageList;
    }

    public Application() {
    }
    public boolean add(UserPage userPage){
        return userPageList.add(userPage);
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
