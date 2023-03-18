/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.isil.jupiter_dae1.model.AlumnoNotas;
import pe.isil.jupiter_dae1.model.Categoria;

/**
 *
 * @author RL123
 */
public class AD_AlumnoNotas {
    
    //para ejecutar consulta
    private PreparedStatement pst=null;
    //para seleccionar filas de una tabla
    private ResultSet rst;
    
    public boolean Insertar(AlumnoNotas alumnonotas){
        
        boolean resultado= false;
        Connection Conexion =null;
        
        try {
            Conexion =ConexionDB.getInstancia().getConexion();
            
            if(Conexion !=null){
                String SQL= "INSERT INTO alumno_notas(alumno, curso, ep1, ep2, ep3, ep4, promedio_eps, ep_parcial,"
                        + "ep_final, nota) VALUES(?,?,?,?,?,?,?,?,?,?)";
                pst =Conexion.prepareStatement(SQL);
                pst.setString(1,alumnonotas.getAlumno());
                pst.setString(2,alumnonotas.getCurso());
                pst.setDouble(3,alumnonotas.getEp1());
                pst.setDouble(4,alumnonotas.getEp2());
                pst.setDouble(5,alumnonotas.getEp3());
                pst.setDouble(6,alumnonotas.getEp4());
                pst.setDouble(7,alumnonotas.getPromedio_eps());
                pst.setDouble(8,alumnonotas.getEp_parcial());
                pst.setDouble(9,alumnonotas.getEp_final());
                pst.setDouble(10,alumnonotas.getNota());
                
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
