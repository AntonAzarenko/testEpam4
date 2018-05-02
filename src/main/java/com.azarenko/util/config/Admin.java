package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "admin", name = "http://www.azarenko.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Admin", propOrder = "page")
public class Admin {
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlElement(required = true)
    @XmlID
    private String page;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "page='" + page + '\'' +
                '}';
    }

    public Admin(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
