package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Application {
    @XmlElementWrapper(name = "adminPages")
    @XmlElement(name = "page")
    private List<String> adminPageList = new ArrayList<>();

    @XmlElementWrapper(name = "userPages")
    @XmlElement(name = "page")
    private List<String> userPageList = new ArrayList<>();

    public Application() {
    }

    public List<String> getAdminPageList() {
        return adminPageList;
    }

    public void setAdminPageList(List<String> adminPageList) {
        this.adminPageList = adminPageList;
    }

    public List<String> getUserPageList() {
        return userPageList;
    }

    public void setUserPageList(List<String> userPageList) {
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
