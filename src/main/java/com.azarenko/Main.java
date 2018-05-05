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
           /* List<AdminPages> adminList = application.getAdminList();
            List<UserPages> userList = application.getUserList();*/
           /* for(AdminPages pair : adminList){
                System.out.println(adminList.size());
            }
            for(UserPages pair : userList){
                System.out.println(pair.getPage());

            }*/

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
