package com.azarenko.util.config;

import com.azarenko.Main;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class RoleLoader {
    private final Logger log = Logger.getLogger(RoleLoader.class);
    private List<String> adminPages;
    private List<String> userPages;
    private static RoleLoader roleLoader;

    private RoleLoader(){
        adminPages = loadAdminPages();
        userPages  = loadUserPages();
    }

    private List<String> loadUserPages() {
        JAXBContext jc = null;
        List<String> user = new ArrayList<>();
        try {
            jc = JAXBContext.newInstance(Application.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(System.getProperty("user.dir") +"/config.xml");
            log.info("read user");
            Application application = (Application) u.unmarshal(reader);
            user = application.getAdminPageList();
        } catch (JAXBException e) {
           log.error(e);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
        return user;
    }

    public List<String> getAdminPages() {
        return adminPages;
    }

    public List<String> getUserPages() {
        return userPages;
    }

    public void setAdminPages(List<String> adminPages) {

        this.adminPages = adminPages;
    }

    public void setUserPages(List<String> userPages) {
        this.userPages = userPages;
    }

    public static RoleLoader getInstance(){
        if(roleLoader == null){
            roleLoader = new RoleLoader();
        }else
            return roleLoader;
        return roleLoader;
    }

    private List<String> loadAdminPages(){
        JAXBContext jc = null;
        List<String> admin = new ArrayList<>();
        try {
            jc = JAXBContext.newInstance(Application.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(System.getProperty("user.dir") +"/config.xml");
            log.info("read admin");
            Application application1 = (Application) u.unmarshal(reader);
            admin = application1.getAdminPageList();
        } catch (JAXBException e) {
           log.info(e);
        } catch (FileNotFoundException e) {
            log.info(e);
        }
        return admin;
    }
}
