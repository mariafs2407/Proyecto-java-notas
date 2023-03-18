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
import pe.isil.jupiter_dae1.model.Cliente;
/**
 *
 * @author maria
 */
public class AD_Cliente {
      //para ejecutar consulta
    private PreparedStatement pst=null;
    //para seleccionar filas de una tabla
    private ResultSet rst;
    
    public boolean Insertar(Cliente cliente){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO cliente(dni,nombres_apellidos,direccion,telefono) "
                        + "VALUES(?,?,?,?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,cliente.getDni());
                pst.setString(2, cliente.getNombres_apellidos());
                pst.setString(3, cliente.getDireccion());
                pst.setString(4, cliente.getTelefono());
                
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
    
    public boolean Actualizar(Cliente cliente){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE cliente SET dni=? , nombres_apellidos=?,"
                        + "direccion=? ,telefono=?  WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,cliente.getDni());
                pst.setString(2,cliente.getNombres_apellidos());
                pst.setString(3,cliente.getDireccion());
                pst.setString(4,cliente.getTelefono());
                
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
                String SQL= "DELETE FROM cliente  WHERE id=?";
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
    
    public List<Cliente> getAll(){
        List<Cliente> listado =new ArrayList<Cliente>();
        Cliente cliente;
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT * FROM cliente";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    cliente=new Cliente();
                    cliente.setId(rst.getInt("id"));
                    cliente.setDni(rst.getString("dni"));   
                    cliente.setNombres_apellidos(rst.getString("nombres_apellidos")); 
                    cliente.setDireccion(rst.getString("direccion")); 
                    cliente.setTelefono(rst.getString("telefono")); 
                    listado.add(cliente);
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
    
    public Cliente get(Integer id){
        Cliente cliente = new Cliente();
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion != null){
                String SQL= "SELECT * FROM cliente WHERE id= ?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,id);
                
                rst = pst.executeQuery();
                while(rst.next()){                   
                    cliente.setId(rst.getInt("id"));
                    cliente.setDni(rst.getString("dni"));   
                    cliente.setNombres_apellidos(rst.getString("nombres_apellidos"));  
                    cliente.setDireccion(rst.getString("direccion"));  
                    cliente.setTelefono(rst.getString("telefono"));  
                    
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return cliente;
    }
}
