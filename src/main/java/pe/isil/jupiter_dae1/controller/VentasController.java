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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.isil.jupiter_dae1.dao.AD_Producto;
import pe.isil.jupiter_dae1.dao.AD_Venta;
import pe.isil.jupiter_dae1.model.Cliente;
import pe.isil.jupiter_dae1.model.Producto;
import pe.isil.jupiter_dae1.model.Venta;
import pe.isil.jupiter_dae1.model.VentaDetalle;

/**
 *
 * @author maria
 */
@WebServlet(name = "VentasController", urlPatterns = {"/admin/ventas/*"})
public class VentasController extends HttpServlet {

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
            out.println("<title>Servlet VentasController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VentasController at " + request.getContextPath() + "</h1>");
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
        String URL = request.getPathInfo();
        if(URL== null || URL.equals("/")){
            request.getRequestDispatcher("/ventas/index.jsp").forward(request, response);
                  
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
        if(URL == null || URL.equals("/")){
            //1. Obtener los productos
            AD_Producto ad = new AD_Producto();
            ArrayList<String> datos = new ArrayList<>();
            
            String p_nombre = request.getParameter("p_nombre"); //esta variable o parametro viene desde el formulario
            ArrayList<Producto> listado = new ArrayList<>();
            
            if (p_nombre == null) {
                listado = ad.seleccionar("", datos);
            } else {
                datos.add(p_nombre);
                listado = ad.seleccionar("nombre", datos);
            }
            
            //2.agregar el listado como atributo para la vista
            request.setAttribute("listado", listado);
            
            //3. Enviar a la vista-index el listado
            request.getRequestDispatcher("/ventas/tabla.jsp").forward(request, response);            
        }
        else
        {
            URL = request.getPathInfo();
            String accion = URL.substring(1);
            String ruta[] = accion.split("/");
            
            String producto_id = request.getParameter("producto_id");
            String cantidad = request.getParameter("cantidad");
            
            HttpSession sesion_carrito = request.getSession(false);
            ArrayList<VentaDetalle> carrito = new ArrayList<>();
            
            switch (ruta[0]) {
                case "agregar_producto":
                    
                    if (sesion_carrito.getAttribute("carrito") != null) {
                        carrito = (ArrayList<VentaDetalle>)sesion_carrito.getAttribute("carrito");
                    }
                    
                    boolean existe = false;
                    int item = 0;
                    
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
                        
                        VentaDetalle detalle = new VentaDetalle();
                        detalle.setProducto_id(producto.getId());
                        detalle.setCantidad(Integer.parseInt(cantidad));
                        detalle.setPrecio(producto.getPrecio());
                        detalle.setTotal( Integer.parseInt(cantidad) * producto.getPrecio());
                        
                        carrito.add(detalle);                        
                    }
                    
                    sesion_carrito.setAttribute("carrito", carrito);
                    
                    request.setAttribute("listado", carrito);
                    request.getRequestDispatcher("/ventas/carrito.jsp").forward(request, response);
                    
                    break;
                
                case "guardar":
                    
                    String fecha = request.getParameter("fecha");
                    
                    
                    String dni = request.getParameter("dni");                    
                    String nombres_apellidos = request.getParameter("nombres_apellidos");
                    String direccion = request.getParameter("direccion");
                    
                    if (sesion_carrito.getAttribute("carrito") != null) {
                        carrito = (ArrayList<VentaDetalle>)sesion_carrito.getAttribute("carrito");
                    }
                    
                    double total = 0;
                    
                    if (carrito != null) {
                        for (int i = 0; i < carrito.size(); i++) {                           
                            total += carrito.get(i).getTotal();
                        }
                    }

                    Cliente cliente = new Cliente();
                    cliente.setDni(dni);
                    cliente.setNombres_apellidos(nombres_apellidos);
                    cliente.setDireccion(direccion);
                    
                    AD_Venta ad_v = new AD_Venta();
                    Venta venta = new Venta();
                    
                    venta.setCliente_id(0);
                    venta.setFecha(Date.valueOf(fecha));
                    venta.setTotal(total);
                    venta.setTotal_items(carrito.size());

                    boolean resultado = false;
                    try {
                        resultado = ad_v.Insertar(venta, carrito, cliente);
                    } catch (SQLException ex) {
                        Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    if(resultado == true){
                        fecha = "";
                        dni = "";
                        nombres_apellidos = "";
                        direccion = "";
                        sesion_carrito.setAttribute("carrito", null);
 
                    
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
                    sesion_carrito.setAttribute("carrito", null);
                    
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
