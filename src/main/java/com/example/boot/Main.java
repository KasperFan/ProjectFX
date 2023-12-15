package com.example.boot;

import com.example.utils.DBUtil;
import com.example.utils.SHA256;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) throws Exception {
//        File output = new File("./account.csv");
//        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)));
        Scanner sc = new Scanner(System.in);
        TreeMap<String, String> account = new TreeMap<>();
        LinkedList<String> names = new LinkedList<>();
        var conn = DBUtil.dbUtil("Java_CADS", "root", new String(new byte[]{70, 90, 104, 95, 51, 57, 52, 48, 50, 50, 54}));
        var sql = "SELECT * FROM user_list";
        try (ResultSet rst = conn.executeQuery(sql)
        ) {
            while (rst.next()) {
                account.put(rst.getString(3), rst.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
//      UPDATE `Java_CADS`.`user_list` SET `pswd_sha` = '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918' WHERE `SID` = 1
//        for (var i :
//                names) {
////            System.out.println(i + "," + SHA256.getSHA256(account.get(i)));//account.get(i));
//            System.out.println(conn.executeUpdate("UPDATE `user_list` SET `pswd_sha` = '" + SHA256.getSHA256(account.get(i)) + "' WHERE `name` = '" + i + "'"));
//        }
        for (int i = 0; i < 3; i++) {
            System.out.println(account.get(sc.next()).equals(SHA256.getSHA256(sc.next())));
        }
        conn.close();
    }
}
