/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.isil.jupiter_dae1.model.Categoria;

/**
 *
 * @author maria
 */
public class AD_Categoria {
    
    //para ejecutar consulta
    private PreparedStatement pst=null;
    //para seleccionar filas de una tabla
    private ResultSet rst;
    
    public boolean Insertar(Categoria categoria){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO categoria(nombre) VALUES(?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,categoria.getNombre());
                
                int res=0;
                res = pst.executeUpdate();
                if(res >0){
                    resultado=true;                   
                }else{
                    resultado=false;
                }
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return resultado;
    }
    
    public boolean Actualizar(Categoria categoria){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE categoria SET nombre = ? WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,categoria.getNombre());
                pst.setInt(2,categoria.getId());
                
                int res=0;
                res = pst.executeUpdate();
                if(res >0){
                    resultado=true;                   
                }else{
                    resultado=false;
                }
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return resultado;
    }
    
    public boolean Eliminar(Integer id){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "DELETE FROM categoria  WHERE id=?";
                pst =Conexion.prepareStatement(SQL);              
                pst.setInt(1,id);
                
                int res=0;
                res = pst.executeUpdate();
                if(res >0){
                    resultado=true;                   
                }else{
                    resultado=false;
                }
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return resultado;
    }
    
    //Obtener todos lo registros de categoria
    public List<Categoria> getAll(){
        List<Categoria> listado =new ArrayList<Categoria>();
        Categoria categoria;
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT * FROM categoria";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    categoria =new Categoria();
                    categoria.setId(rst.getInt("id"));
                    categoria.setNombre(rst.getString("nombre"));   
                    
                    listado.add(categoria);
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return listado;
    }
    
    
    //Obtener una categoria 
    public Categoria get(Integer id){
        Categoria categoria = new Categoria();
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion != null){
                String SQL= "SELECT * FROM categoria WHERE id= ?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,id);
                
                rst = pst.executeQuery();
                while(rst.next()){                   
                    categoria.setId(rst.getInt("id"));
                    categoria.setNombre(rst.getString("nombre"));                 
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return categoria;
    }

}
