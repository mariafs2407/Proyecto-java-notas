/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author maria
 */
public class ConexionDB {
    private Connection conexion;
    private static ConexionDB instancia;
    
    Dotenv dotenv= Dotenv.load();
    
    private final String username =dotenv.get("DB_USERNAME");
    private final String password =dotenv.get("DB_PASSWORD");
    private final String host =dotenv.get("DB_HOST");
    private final String database =dotenv.get("DB_DATABASE");
    private final String puerto =dotenv.get("DB_PORT");
    
    //Constructor es la primera funcion que se ejecuta cuando creamos un objeto de clase
    private ConexionDB()
    {
        try {
            
            //PASO 01: Definir los parametros de la conexion
            /*String username = "root";
            String password = "root";
            String host = "localhost";
            String database = "dae1";
            String puerto = "3306";*/
            
            //PASO 02: Cargar el driver o controlador o dependencia de MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            //PASO 03: Conectarnos a la BD
            String url = "jdbc:mysql://" + host + ":" + puerto + "/" + database + 
                    "?useSSL=false&serverTimezone=America/Lima";
            this.conexion = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión a db exitosa!");
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Connection getConexion() throws SQLException{
        if(conexion == null)
            instancia = new ConexionDB();    
        return this.conexion;
    }
    
    public static ConexionDB getInstancia(){
    
        if (instancia != null) {
            return instancia;
        }
        instancia = new ConexionDB();
        return instancia;
    }
    
    public void close(Connection conexion){
        try {
            conexion.close();
            instancia = null;
            if (conexion.isClosed()) {
                System.out.println("Se cerro la conexión a la db");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
