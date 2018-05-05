package com.azarenko;

import com.azarenko.util.config.Application;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try{
            JAXBContext jc  = JAXBContext.newInstance(Application.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("src/main/resources/config.xml");
            Application application = (Application) u.unmarshal(reader);
            System.out.println(application.toString());
           /* List<AdminPage> adminList = application.getAdminList();
            List<UserPage> userList = application.getUserList();*/
           /* for(AdminPage pair : adminList){
                System.out.println(adminList.size());
            }
            for(UserPage pair : userList){
                System.out.println(pair.getPage());

            }*/

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
