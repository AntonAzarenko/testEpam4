package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userPages", propOrder = "page")
public class UserPage {
    @XmlElement(required = true)
    private String page;

    public UserPage() {
    }

    @Override
    public String toString() {
        return "UserPage{" +
                "page='" + page + '\'' +
                '}';
    }

    public UserPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
