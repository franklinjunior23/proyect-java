/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.proyect.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author frijol
 */


import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:postgresql://aws-0-us-west-2.pooler.supabase.com:6543/postgres" +
                    "?sslmode=require&pgbouncer=true&preferQueryMode=simple";
    private static final String USER = "postgres.rgozeinmxdreepdptjyh";
    private static final String PASSWORD = "G1Cmypwpr96pWGf7";

    private Database() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
