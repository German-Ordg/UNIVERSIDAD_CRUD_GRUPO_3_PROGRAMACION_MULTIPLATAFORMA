/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universidad.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JULIO
 */
public class Conn {
    private static Connection c = null;
    
    private Conn(){
        //Do nothing
    }
    
    public static Connection obtenerConexion(){
        try {
            if( c == null){
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:universidad.db");
                return c;
            } else {
                return c;
            }
            
        } catch ( Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    
}
