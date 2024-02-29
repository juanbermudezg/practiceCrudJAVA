package com.juanbermudez.java_crud_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * @author juanbermudezg
 */
public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "esave2021";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "La conexión se ha realizado con éxito");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error a conectar a la bd, error: "+ e.toString());
        }
        return conectar;
    }
}
