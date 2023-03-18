/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.isil.jupiter_dae1.model.VentaDetalle;

/**
 *
 * @author maria
 */
public class AD_VentaDetalle {
    
    private PreparedStatement pst=null;
    //para seleccionar filas de una tabla
    private ResultSet rst;
    
    //insertar
    public boolean Insertar(VentaDetalle vdetalle){
        boolean resultado= false;
        Connection Conexion= null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO ventadetalle(venta_id,producto_id,cantidad,"
                        + " precio,total) "
                        + "VALUES(?,?,?,?,?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,vdetalle.getVenta_id() );
                pst.setInt(2, vdetalle.getProducto_id());
                pst.setInt(3,vdetalle.getCantidad());
                pst.setDouble(4, vdetalle.getPrecio());
                pst.setDouble(5, vdetalle.getTotal());             
                
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
    
    //actualizar
    public boolean Actualizar(VentaDetalle vdetalle){
        boolean resultado= false;
        Connection Conexion  = null;
        
         try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE ventadetalle SET venta_id =?,producto_id,"
                        + "cantidad=?,precio=?,total=? WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,vdetalle.getVenta_id() );
                pst.setInt(2, vdetalle.getProducto_id());
                pst.setInt(3,vdetalle.getCantidad());
                pst.setDouble(4, vdetalle.getPrecio());
                pst.setDouble(5, vdetalle.getTotal());    
                pst.setInt(6, vdetalle.getId());
                
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
    
    //Eliminar
    public boolean Eliminar(Integer id){
        boolean resultado= false;
        Connection Conexion= null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "DELETE FROM ventadetalle WHERE id=?";
                pst =Conexion.prepareStatement(SQL);               
                pst.setDouble(1,id);             
                
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
    
    
    
    
}
