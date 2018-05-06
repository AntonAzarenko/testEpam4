package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class AdminPage {

    @XmlElement(required = true)
    private String page;

    public AdminPage() {
    }

    @Override
    public String toString() {
        return "AdminPage{" +
                "page='" + page + '\'' +
                '}';
    }

    public AdminPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
