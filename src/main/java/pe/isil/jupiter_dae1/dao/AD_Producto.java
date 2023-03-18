/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import pe.isil.jupiter_dae1.model.Producto;

/**
 *
 * @author maria
 */
public class AD_Producto {
    
    private PreparedStatement pst=null;
    private ResultSet rst;
    
    
    //INSERTAR
    public boolean Insertar(Producto producto){        
        
        boolean resultado= false;
        Connection Conexion =null;
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO producto(categoria_id,nombre,imagen,precio,stock) "
                        + " VALUES(?,?,?,?,?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,producto.getCategoria_id());
                pst.setString(2, producto.getNombre());
                pst.setBlob(3,producto.getImagen());//ruta
                pst.setDouble(4, producto.getPrecio());
                pst.setInt(5, producto.getStock());                
                
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
    public boolean Actualizar(Producto producto){        
        boolean resultado= false;
        Connection Conexion= null;
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE producto SET categoria_id = ? ,nombre = ?,imagen = ?,precio = ? ,"
                        + " stock = ?  WHERE id = ?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1,producto.getCategoria_id());
                pst.setString(2, producto.getNombre());
                pst.setBlob(3,producto.getImagen());
                pst.setDouble(4, producto.getPrecio());
                pst.setInt(5, producto.getStock());
                pst.setInt(6, producto.getId());
                
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
        
        boolean resultado= false;
        Connection Conexion= null;
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "DELETE FROM producto WHERE id= ?";
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
    
    //listar productos
    public List<Producto> getAll(){
        List<Producto> listado= new ArrayList<Producto>();
        Producto producto;
        Connection Conexion=null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM producto";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    producto = new Producto();
                    producto.setId(rst.getInt(1));
                    producto.setCategoria_id(rst.getInt(2));
                    producto.setNombre(rst.getString(3));
                    producto.setImagen(rst.getBinaryStream(4));
                    producto.setPrecio(rst.getDouble(5));
                    producto.setStock(rst.getInt(6));                    
                    
                    listado.add(producto);
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
    
    //LISTAR IMG
    public void listarImg(int id,HttpServletResponse response){    
        Connection Conexion=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        response.setContentType("image/*");
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){         
                String SQL= "SELECT*FROM producto where id="+id; 
                pst =Conexion.prepareStatement(SQL);
                outputStream=response.getOutputStream();
                
                rst = pst.executeQuery();
                if(rst.next()){
                    inputStream=rst.getBinaryStream("imagen");                    
                }                
                bufferedInputStream=new BufferedInputStream(inputStream);
                bufferedOutputStream=new BufferedOutputStream(outputStream);
                int i=0;
                while((i=bufferedInputStream.read())!= -1){
                    bufferedOutputStream.write(i);
                }
                inputStream.close();
                outputStream.close();
                bufferedInputStream.close();
                bufferedOutputStream.close();
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());//mostrar mensaje de error 
        }
    }
    //Obtener un producto:CONSULTAR
    public Producto get(Integer id){
        Producto producto= new Producto();
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM producto WHERE id = ?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                
                rst = pst.executeQuery();
                while(rst.next()){                   
                    producto.setId(rst.getInt("id"));
                    producto.setCategoria_id(rst.getInt("categoria_id"));
                    producto.setNombre(rst.getString("nombre"));
                    producto.setImagen(rst.getBinaryStream("imagen"));
                    producto.setPrecio(rst.getDouble("precio"));
                    producto.setStock(rst.getInt("stock"));           
                                  
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return producto;
    }
    
    //para ventas y orden
    public ArrayList<Producto> seleccionar(String filtro,ArrayList<String> datos){
        ArrayList<Producto> lista= new ArrayList<>();
        Producto producto;
        Connection Conexion= null;
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL="";
                switch(filtro){
                    case "id":
                        SQL="SELECT *FROM producto WHERE id=?";
                        pst=Conexion.prepareStatement(SQL);
                        pst.setInt(1, Integer.parseInt(datos.get(0)));
                        break;
                    case "nombre":
                        SQL="SELECT* FROM producto WHERE nombre LIKE ?";
                        pst=Conexion.prepareStatement(SQL);
                        pst.setString(1, "%"+datos.get(0)+"%");
                        break;
                    default:
                        SQL="SELECT*FROM producto";
                        pst=Conexion.prepareStatement(SQL);
                        break;
                }
                //ejecutamos la consulta y lo guardamos en el resultset rst
                rst=pst.executeQuery();
                
                while(rst.next()){//instancia
                    producto = new Producto();//creo una copia e inicializo
                    producto.setId(rst.getInt("id"));
                    producto.setNombre(rst.getString("nombre"));
                    producto.setPrecio(rst.getDouble("precio"));
                    producto.setStock(rst.getInt("stock"));   
                    //Nota:cambiar la categoria enla base de datos de int a strign
                    producto.setCategoria(rst.getString("categoria"));
                    lista.add(producto);//agregamos ala lista el producto
                }
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return lista;
    }
}
