package com.azarenko;

import com.azarenko.util.DBUtil;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
    }
}
