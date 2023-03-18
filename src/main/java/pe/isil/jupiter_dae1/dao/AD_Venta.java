/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.isil.jupiter_dae1.model.Cliente;
import pe.isil.jupiter_dae1.model.Venta;
import pe.isil.jupiter_dae1.model.VentaDetalle;

/**
 *
 * @author maria
 */
public class AD_Venta {
    private PreparedStatement pst=null;
    private ResultSet rst;
    
    //INSERTAR
    public boolean  Insertar(Venta venta,ArrayList<VentaDetalle> detalle,Cliente cliente) throws SQLException{
        boolean resultado= false;
        Connection Conexion =null;
        
          try {
            Conexion = ConexionDB.getInstancia().getConexion();
            if (Conexion != null) {
                
                Conexion.setAutoCommit(false); //deshabilitamos el commit auotmatico
                String SQL = "INSERT INTO Cliente(dni,nombres_apellidos, direccion) VALUES (?, ?, ?)";
                pst = Conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, cliente.getDni());
                pst.setString(2, cliente.getNombres_apellidos());
                pst.setString(3, cliente.getDireccion());
                pst.executeUpdate();
                
                //obtener el ID de la venta que acabamos de registrar
                ResultSet generaId_cliente = pst.getGeneratedKeys();
                Integer id_generado_cliente = 0;
                if (generaId_cliente.next()) {
                    id_generado_cliente = generaId_cliente.getInt(1);
                }
                
                pst = null;
                SQL = "INSERT INTO venta(fecha, total, cliente_id, total_items) VALUES(?, ?, ?, ?)";
                pst = Conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setDate(1, venta.getFecha());
                pst.setDouble(2, venta.getTotal());
                pst.setInt(3, id_generado_cliente ) ;
                pst.setInt(4, venta.getTotal_items());
                
                pst.executeUpdate();
                
                //obtener el ID de la venta que acabamos de registrar
                ResultSet generaId = pst.getGeneratedKeys();
                Integer id_generado = 0;
                if (generaId.next()) {
                    id_generado = generaId.getInt(1);
                }
                
                //insertar el detalle
                for (int i = 0; i < detalle.size(); i++) {
                    SQL = "INSERT INTO venta_detalle(venta_id, producto_id, cantidad, precio, total) VALUES(?, ?, ?, ?, ?)";
                    pst = Conexion.prepareStatement(SQL);
                    pst.setInt(1, id_generado);
                    pst.setInt(2, detalle.get(i).getProducto_id());
                    pst.setInt(3, detalle.get(i).getCantidad());
                    pst.setDouble(4, detalle.get(i).getPrecio());
                    pst.setDouble(5, detalle.get(i).getTotal());
                    pst.executeUpdate();
                    
                }
                
                Conexion.commit(); //Confirmamos las transacciones en la bd
                resultado = true;                
                                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
            if(Conexion != null){
                Conexion.rollback();//revertimos los cambios en la bd
            }
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return resultado;
    }
    
    //ACTUALIZAR
    public boolean Actualizar(Venta venta){
        boolean resultado= false;
        Connection Conexion=null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "UPDATE venta SET fecha = ?,total = ?,"
                        + " usuario_id = ?  WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1, venta.getFecha().toString());
                pst.setDouble(2, venta.getTotal());
               // pst.setInt(3, venta.getUsuario_id());
                pst.setInt(4, venta.getId());
                
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
                String SQL= "DELETE FROM venta WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                pst.setInt(1, id);
                
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
    
    //LISTAR
    public List<Venta> getAll(){
        List<Venta> listar= new ArrayList<Venta>();
        Venta venta;
        Connection Conexion=null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM venta";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    venta = new Venta();
                    //venta.setFecha(LocalDateTime.parse(rst.getString("fecha")));
                    venta.setTotal(rst.getDouble("total"));
                   // venta.setUsuario_id(rst.getInt("usuario_id"));                       
                    
                    listar.add(venta);
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
    
    //mostra venta
    public Venta get(Integer id){
        Venta venta = new Venta();
        Connection Conexion = null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            if(Conexion !=null){
                String SQL= "SELECT*FROM venta WHERE id=?";
                pst =Conexion.prepareStatement(SQL);
                
                rst = pst.executeQuery();
                while(rst.next()){
                    venta = new Venta();
                   // venta.setFecha(LocalDateTime.parse(rst.getString("fecha")));
                    venta.setTotal(rst.getDouble("total"));
                  //  venta.setUsuario_id(rst.getInt("usuario_id"));                       
                    
                }
                
            }else{
                System.out.println("Error en la conexion ala bd");
            }
        } catch (SQLException e) {
             System.out.println(e.getMessage());//mostrar mensaje de error       
        } finally {
             ConexionDB.getInstancia().close(Conexion);
        }
        return venta;
    }
    
    
}
