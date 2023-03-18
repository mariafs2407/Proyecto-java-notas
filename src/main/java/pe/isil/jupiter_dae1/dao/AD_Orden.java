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
import pe.isil.jupiter_dae1.model.Orden;
import pe.isil.jupiter_dae1.model.OrdenDetalle;
import pe.isil.jupiter_dae1.model.Producto;
import pe.isil.jupiter_dae1.model.Proveedor;

/**
 *
 * @author maria
 */
public class AD_Orden {
     private PreparedStatement pst = null;
    private ResultSet rst;
    
    //INSERTAR
    public boolean  Insertar(Orden orden,ArrayList<OrdenDetalle> detalle,Proveedor proveedor,
            ArrayList<Integer> id_producto,ArrayList<Integer> cantidad_ordenada) throws SQLException{
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                Conexion.setAutoCommit(false); //deshabilitamos el commit auotmatico
                String SQL = "INSERT INTO proveedor(ruc,raz_social,direccion) VALUES (?, ?, ?)";
                pst = Conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, proveedor.getRuc());
                pst.setString(2, proveedor.getRaz_social());
                pst.setString(3, proveedor.getDireccion());
                pst.executeUpdate();
                
                //obtener el ID de la venta que acabamos de registrar
                ResultSet generaId_proveedor = pst.getGeneratedKeys();
                Integer id_generado_proveedor = 0;
                if (generaId_proveedor.next()) {
                    id_generado_proveedor = generaId_proveedor.getInt(1);
                }
                
                pst = null;
                //INSERCION DE UNA ORDEN
                SQL = "INSERT INTO orden(fecha, proveedor_id, subtotal, total, igv) VALUES(?, ?, ?, ?, ?)";
                pst = Conexion.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
                pst.setDate(1, orden.getFecha());
                pst.setInt(2, id_generado_proveedor);
                pst.setDouble(3, orden.getSubtotal() ) ;
                pst.setDouble(4, orden.getTotal());
                pst.setDouble(5, orden.getIgv());
                
                pst.executeUpdate();
                
                //obtener el ID de la orden que acabamos de registrar
                ResultSet generaId = pst.getGeneratedKeys();
                Integer id_generado = 0;
                if (generaId.next()) {
                    id_generado = generaId.getInt(1);
                }
                
                //insertar el detalle
                Integer prueba=cantidad_ordenada.get(1);
                Integer prueb2=id_producto.get(1);
                
                for (int i = 0; i < detalle.size(); i++) {
                    SQL = "INSERT INTO detalle_orden(orden_id, producto_id, cantidad, precio, total) VALUES(?, ?, ?, ?, ?)";
                    pst = Conexion.prepareStatement(SQL);
                    pst.setInt(1, id_generado);
                    pst.setInt(2, detalle.get(i).getProducto_id());
                    pst.setInt(3, detalle.get(i).getCantidad());
                    pst.setDouble(4, detalle.get(i).getPrecio());
                    pst.setDouble(5, detalle.get(i).getTotal());
                    pst.executeUpdate(); 
                    pst = null;
                    
                    SQL="UPDATE producto set stock= stock + ?  where id=?";
                    pst = Conexion.prepareStatement(SQL);
                    pst.setInt(1,cantidad_ordenada.get(i));
                    pst.setInt(2,id_producto.get(i));
                    pst.executeUpdate();
                    
                }
                Conexion.commit();//confirmamos las transacciones en la bd
                resultado= true;
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
}
