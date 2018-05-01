package com.azarenko;

import com.azarenko.util.DBUtil;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Calendar startC = Calendar.getInstance();
        Calendar endC = Calendar.getInstance();
        Date date = new Date();
        startC.setTime(date);

        System.out.println(date);
        startC.add(Calendar.DATE,150);
        date = startC.getTime();
        System.out.println(date);



    }
}
