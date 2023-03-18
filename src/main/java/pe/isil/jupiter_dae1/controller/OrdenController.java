/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pe.isil.jupiter_dae1.dao.AD_Orden;
import pe.isil.jupiter_dae1.dao.AD_Producto;
import pe.isil.jupiter_dae1.model.Orden;
import pe.isil.jupiter_dae1.model.OrdenDetalle;
import pe.isil.jupiter_dae1.model.Producto;
import pe.isil.jupiter_dae1.model.Proveedor;

/**
 *
 * @author maria
 */
@WebServlet(name = "OrdenController", urlPatterns = {"/admin/orden/*"})
public class OrdenController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrdenController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrdenController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String URL= request.getPathInfo();
        if(URL== null || URL.equals("/")){
            request.getRequestDispatcher("/ordenes/index.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String URL = request.getPathInfo();
        
        //mostrar los productos que tiene el carrito
        if(URL== null || URL.equals("/")){
            //1.obtener los productos:
            AD_Producto ad=new AD_Producto();
            //para los filtro y datos
            ArrayList<String> datos= new ArrayList<>();
            
            //viene desde el formulario
            String p_nombre=request.getParameter("p_nombre");
            ArrayList<Producto> listado= new ArrayList<>();
            
            if(p_nombre==null){
                //vacio-default:todos los productos
                listado=ad.seleccionar("", datos);
            }else{
                datos.add(p_nombre);
                listado=ad.seleccionar("nombre", datos);
            }
            
            //2.agregar el listado como atributo para la vista
            request.setAttribute("listado", listado);
            
            //3.enviar los datos a la vistaIndex
            request.getRequestDispatcher("/ordenes/tabla.jsp").forward(request, response);
            
        }else{
            //obtener la url
            URL= request.getPathInfo();
            String accion= URL.substring(1);
            String ruta[] =accion.split("/");
            
            String producto_id = request.getParameter("producto_id");
            String cantidad = request.getParameter("cantidad");
            
            HttpSession session_carrito=request.getSession(false);
            ArrayList<OrdenDetalle> carrito= new ArrayList<>();
            
            switch (ruta[0]) {
                case "agregar":
                    //si la session carrito es existe,(tienen items)
                    if(session_carrito.getAttribute("carrito")!=null){
                        carrito=(ArrayList<OrdenDetalle>) session_carrito.getAttribute("carrito");
                    }
                    
                    //sino existe el producto lo agregamos
                    boolean existe=false;
                    int item=0;
                    
                    if (carrito != null) {
                        for (int i = 0; i < carrito.size(); i++) {
                            if (carrito.get(i).getProducto_id() == Integer.parseInt(producto_id)) {
                                item = i;
                                existe = true;
                                break;
                            }
                        }
                    }
                    
                    if(existe){
                        carrito.get(item).setCantidad(carrito.get(item).getCantidad() + Integer.parseInt(cantidad));
                    }
                    
                    else
                    {
                        ArrayList<String> datos = new ArrayList<>();
                        datos.add(producto_id);
                        Producto producto = new Producto();
                        
                        AD_Producto ad = new AD_Producto();
                        producto = ad.seleccionar("id", datos).get(0);
                        
                        OrdenDetalle detalle = new OrdenDetalle();
                        detalle.setProducto_id(producto.getId());
                        detalle.setCantidad(Integer.parseInt(cantidad));
                        detalle.setPrecio(producto.getPrecio());
                        detalle.setTotal( Integer.parseInt(cantidad) * producto.getPrecio());
                        
                        carrito.add(detalle);                        
                    }
                    
                    session_carrito.setAttribute("carrito", carrito);
                    request.setAttribute("listado", carrito);
                    
                    request.getRequestDispatcher("/ordenes/carrito.jsp").forward(request, response);
                    
                    break;
                
                case "guardar":
                    //del formulario:
                    String fecha = request.getParameter("fecha");
                    String ruc= request.getParameter("ruc");
                    String raz_social=request.getParameter("raz_social");
                    String direccion= request.getParameter("direccion"); 
                    
                    if (session_carrito.getAttribute("carrito") != null) {
                        carrito = (ArrayList<OrdenDetalle>)session_carrito.getAttribute("carrito");
                    }
                    
                    //calculando el total y subtotal:
                    double total = 0;
                    double precio=0;                   
                    
                    ArrayList<Integer> id_producto = new ArrayList<>();
                    ArrayList<Integer> cantidad_ordenada = new ArrayList<>();
                    
                    if (carrito != null) {
                        for (int i = 0; i < carrito.size(); i++) {  
                            precio =(carrito.get(i).getCantidad()*carrito.get(i).getPrecio());  
                            total = precio + total ;
                            id_producto.add( carrito.get(i).getProducto_id());//
                            cantidad_ordenada.add(carrito.get(i).getCantidad());
                        }                        
                    }
                    double subtotal=(total/1.18);
                    
                    Integer prueba= id_producto.get(1);
                    
                    //proveedor...
                    Proveedor proveedor =new Proveedor();
                    proveedor.setRuc(ruc);
                    proveedor.setRaz_social(raz_social);
                    proveedor.setDireccion(direccion);
                    
                    //orden de compra...
                    AD_Orden ad_orden= new AD_Orden();
                    Orden orden= new Orden();
                    
                    orden.setProveedor_id(0);
                    orden.setFecha(Date.valueOf(fecha));
                    orden.setSubtotal(subtotal);
                    orden.setIgv(0.18);
                    orden.setTotal(total);
                    
                    //producto ...
                    //cantidad debe ser actualizada ,aumentar en stock
                  
                    
                    //insertando...
                    boolean resultado = false;                
                    try {
                         resultado = ad_orden.Insertar(orden, carrito, proveedor,id_producto,cantidad_ordenada);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                    
                    //Respuesta ...
                    if(resultado == true){
                        fecha = "";
                        ruc = "";
                        raz_social= "";
                        direccion = "";
                        session_carrito.setAttribute("carrito", null);
 
                    
                        response.setContentType("text/html; charset=UTF-8");
                        PrintWriter msg = response.getWriter();
                        msg.print("Registro correcto"); 
                        }
                    else
                    {
                        response.setContentType("text/html; charset=UTF-8");
                        PrintWriter msg = response.getWriter();
                        msg.print("Error en el registro de la venta");
                    }
                    
                    break;

                case "cancelar":
                    session_carrito.setAttribute("carrito", null);
                    break;

                default:
                    throw new AssertionError();
               
            }
        
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
