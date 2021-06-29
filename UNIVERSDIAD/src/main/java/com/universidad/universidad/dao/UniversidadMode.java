/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universidad.universidad.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author JULIO
 */
public class UniversidadMode{
    public static void verificarTablaUniversidades(){
    try{
             Connection conn = Conn.obtenerConexion();
        
             String SQLCrearTabla = "CREATE TABLE IF NOT EXISTS universidad"
                        + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                        + " SIGLAS TEXT NOT NULL,"
                        + " UNIVERSIDAD TEXT NOT NULL,"
                        + " RECTOR TEXT NOT NULL"
                        + ")";
                Statement comandoSql = conn.createStatement();
                comandoSql.executeUpdate(SQLCrearTabla);
                comandoSql.close();
        }catch(Exception ex){
            //
            System.err.println(ex.getMessage());
        }
    }

    
    public static ArrayList<Rectores> getRectores(){
        ArrayList<Rectores> Rectores = new ArrayList<Rectores>();
        
        try {
            Connection conn = Conn.obtenerConexion();

            String SQLGetRectores = "Select * FROM universidad;";
            Statement comandoSql = conn.createStatement();
            ResultSet cursorUniversidad = comandoSql.executeQuery(SQLGetRectores);
            while( cursorUniversidad.next() ){
                Rectores _miRector = new Rectores();
                _miRector.setID(cursorUniversidad.getInt("ID"));
                _miRector.setSIGLAS(cursorUniversidad.getString("SIGLAS"));
                _miRector.setNOMBREUNIVERSIDAD(cursorUniversidad.getString("UNIVERSIDAD"));
                _miRector.setNOMBRECAMPUS(cursorUniversidad.getString("RECTOR"));
                
                
                Rectores.add(_miRector);
            }
            comandoSql.close();

            return Rectores;
        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
            return Rectores;
        }
    }
    
    public static Rectores getRectoresById(double Id){
        Rectores _miRector = new Rectores();
        
        try {
            Connection conn = Conn.obtenerConexion();

            String SQLGetRectores = String.format("Select * FROM universidad where ID=%s;", String.valueOf(Id));
            System.out.println(SQLGetRectores);
            Statement comandoSql = conn.createStatement();
            ResultSet cursorUniversidad = comandoSql.executeQuery(SQLGetRectores);
            while( cursorUniversidad.next() ){
               _miRector.setID(cursorUniversidad.getInt("ID"));
                _miRector.setSIGLAS(cursorUniversidad.getString("SIGLAS"));
                _miRector.setNOMBREUNIVERSIDAD(cursorUniversidad.getString("UNIVERSIDAD"));
                _miRector.setNOMBRECAMPUS(cursorUniversidad.getString("RECTOR"));
       
            }
            comandoSql.close();

            return _miRector;
        
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
            return _miRector;
        }
    }
    
    public static boolean agregarRectores(Rectores rectoresAAgregar){
        try{
            String sqlStr = "INSERT INTO universidad (SIGLAS, UNIVERSIDAD, RECTOR) VALUES ('%s', '%s', '%s')";
            
            Connection conn = Conn.obtenerConexion();
            Statement comandoSQL = conn.createStatement();
            int registroAfectados = comandoSQL.executeUpdate(
                    String.format(sqlStr,
                       rectoresAAgregar.getSIGLAS(), 
                       rectoresAAgregar.getNOMBREUNIVERSIDAD(), 
                       rectoresAAgregar.getNOMBRECAMPUS()
                    )
            );
            return registroAfectados > 0;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean editarRectores(Rectores rectoresAEditar){
        try{
            String sqlStr = "UPDATE  universidad set SIGLAS = '%s', UNIVERSIDAD = '%s', RECTOR = '%s' where ID=%f;";
            
            Connection conn = Conn.obtenerConexion();
            Statement comandoSQL = conn.createStatement();
            int registroAfectados = comandoSQL.executeUpdate(
                    String.format(sqlStr,
                       rectoresAEditar.getSIGLAS(), 
                       rectoresAEditar.getNOMBREUNIVERSIDAD(), 
                       rectoresAEditar.getNOMBRECAMPUS(), 
                       rectoresAEditar.getID()
                    )
            );
            return registroAfectados > 0;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean eliminarRectores(Rectores rectoresAEliminar){
        try{
            String sqlStr = "DELETE FROM  universidad where ID=%f;";
            
            Connection conn = Conn.obtenerConexion();
            Statement comandoSQL = conn.createStatement();
            int registroAfectados = comandoSQL.executeUpdate(
                    String.format(sqlStr,
                       rectoresAEliminar.getID()
                    )
            );
            return registroAfectados > 0;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            return false;
        }
    }
    
}
