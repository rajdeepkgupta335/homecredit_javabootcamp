package com.labs.java.jdbc;

import java.sql.*;

public class JdbcEx {
    public static void main(String[] args) {
        System.out.println("JDBC Demo...");
        Connection con = null;
        Statement stat = null ;
        ResultSet rs = null;
        try {
            System.out.println("Loading Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded successfully...");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Error While Loading Driver " + e.getMessage());
        }

        String db_url = "jdbc:mysql://localhost:3306/jdbctraining";

        try {
            con = DriverManager.getConnection(db_url, "training", "training" );
            System.out.println("Connection Successfully Created : " + con);
        } catch (SQLException e) {
            System.out.println("Error While Creating Connection : " + e.getMessage());
            e.printStackTrace();
            return;
        }


        try {
            System.out.println("Creating Statement...");
            stat = con.createStatement();
            System.out.println("Statement created successfully");
        } catch (SQLException e) {
            System.out.println("Error while creating statement : " + e.getMessage());
            e.printStackTrace();
            return;
        }


        String query = "SELECT * FROM employee";

        try {
            System.out.println("Executing Query...");
            rs = stat.executeQuery(query);
            System.out.println("Executed Query and got results " + rs);
        } catch (SQLException e) {
            System.out.println("Error while executing the query : " + e.getMessage());
            e.printStackTrace();
            return;
        }

        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String designation = rs.getString("designation");
                String department = rs.getString("department");
                String country = rs.getString("country");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + designation + "\t" + department + "\t" + country);
            }
        }catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }

    }

