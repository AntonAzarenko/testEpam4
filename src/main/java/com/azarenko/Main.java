package com.azarenko;

import com.azarenko.web.controllers.PeriodicalRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.*;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        ConfigurableApplicationContext cntx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println(Arrays.toString(cntx.getBeanDefinitionNames()));


    }
}
