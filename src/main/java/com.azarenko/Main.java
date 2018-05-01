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
        int day = startC.get(Calendar.DAY_OF_MONTH);
        int month = startC.get(Calendar.MONTH);
        int year = startC.get(Calendar.YEAR);
        int days = 4 * 7;
        System.out.println(date);
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
        int newDays = day + days;
        System.out.println(newDays);

        if (newDays > 30 && newDays < 60) {
            month++;
            day = newDays - 30;
            endC.set(year,month,day);
        }
        Date date1 = endC.getTime();
        System.out.println(date1);


    }
}
