package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "user",namespace = "http://www.azarenko.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = "page")
public class User {
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlElement(required = true)
    private String page;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "page='" + page + '\'' +
                '}';
    }

    public User(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
