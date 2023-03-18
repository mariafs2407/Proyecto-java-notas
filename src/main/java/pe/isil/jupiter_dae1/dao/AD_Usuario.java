/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.isil.jupiter_dae1.model.Usuario;


/**
 *
 * @author maria
 */
public class AD_Usuario {
    
    private PreparedStatement pst=null;
    private ResultSet rst;
    
    //INSERTAR
    public boolean Insertar(Usuario usuario){
        
        boolean resultado =false;
        Connection Conexion=null;
        
        try {
            Conexion = ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO usuario(email,nombre,password)"
                        + "  VALUES(?,?,?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1, usuario.getEmail());
                pst.setString(2, usuario.getNombre());
                pst.setString(3, usuario.getPassword());
                
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
    
    //ACTUALIZAR
    public boolean Actualizar(Usuario usuario){
        
        boolean resultado =false;
        Connection Conexion=null;
        
        try {
            Conexion = ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE usuario SET email = ?,password = ?,nombre = ? "
                        + "   WHERE id=?";                        
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1, usuario.getEmail());
                pst.setString(2, usuario.getNombre());
                pst.setString(3, usuario.getPassword());
                
                pst.setInt(6,usuario.getId());
                
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
    
    //ELIMINAR
    public boolean Eliminar(Integer id){
        
        boolean resultado =false;
        Connection Conexion=null;
        
        try {
            Conexion = ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "DELETE FROM usuario WHERE id= ?";
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
    
    //listar usuarios
    public List<Usuario> getAll(){
        List<Usuario> listar= new ArrayList<Usuario>();
        Usuario usuario;
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM usuario";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    usuario = new Usuario();
                    usuario.setEmail(rst.getString("email"));                    
                    usuario.setPassword(rst.getString("password"));                                      
                    usuario.setNombre(rst.getString("nombre"));
                    listar.add(usuario);
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return listar;
            
    }
    
    //buscar un usuario por email
    public boolean getValidarPorCorreo (Usuario usuario){       
        Connection Conexion= null;        
        boolean resultado =false;
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM usuario WHERE email=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,usuario.getEmail());               
                
                int r=0;
                rst = pst.executeQuery();
                while(rst.next()){ 
                    r=r+1;                    
                    usuario.setId(rst.getInt("id"));                    
                    usuario.setNombre(rst.getString("nombre")); 
                    usuario.setEmail(rst.getString("email"));                    
                    usuario.setPassword(rst.getString("password"));                                                                
                }
                if(r==1){
                    //existen datos
                    resultado=true ;
                }else{
                    //no existen datos- r==0
                    resultado=false;
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");                
            }
        }  catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return resultado;
    }    
        
    public Usuario login(String email){
        Usuario usuario=null;
        Connection conexion=null;
        try{
            conexion =ConexionDB.getInstancia().getConexion();
            if(conexion !=null){
                String SQL="SELECT*FROM usuario WHERE email=?";
                pst=conexion.prepareStatement(SQL);
                pst.setString(1,email);
                rst=pst.executeQuery();
                
                while(rst.next()){
                    usuario= new Usuario(); 
                    usuario.setId(rst.getInt("id"));                    
                    usuario.setNombre(rst.getString("nombre")); 
                    usuario.setEmail(rst.getString("email"));                    
                    usuario.setPassword(rst.getString("password"));                                      
                    
                    
                }
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(conexion);
        }
        return usuario;
    }
    
}   

