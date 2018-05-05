package com.azarenko.util.config;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

@XmlRootElement(namespace = "admin", name = "http://www.azarenko.com")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdminPages", propOrder = "page")
public class AdminPages {
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlElement(required = true)
    @XmlID
    private String page;

    public AdminPages() {
    }

    @Override
    public String toString() {
        return "AdminPages{" +
                "page='" + page + '\'' +
                '}';
    }

    public AdminPages(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
