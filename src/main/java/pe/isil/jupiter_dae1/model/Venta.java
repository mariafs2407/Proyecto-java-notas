/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.isil.jupiter_dae1.model;

import java.sql.Date;


/**
 *
 * @author maria
 */
public class Venta {
    private Integer id;
    private Date fecha;
    private double total;
    private Integer cliente_id;
    private Integer total_items;

    public Venta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer usuario_id) {
        this.cliente_id = usuario_id;
    }

    public Integer getTotal_items() {
        return total_items;
    }

    public void setTotal_items(Integer total_items) {
        this.total_items = total_items;
    }
    
    
}
