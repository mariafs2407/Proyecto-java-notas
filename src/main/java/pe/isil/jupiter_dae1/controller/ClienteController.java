/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.isil.jupiter_dae1.dao.AD_Cliente;
import pe.isil.jupiter_dae1.model.Cliente;

/**
 *
 * @author maria
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/admin/clientes/*"})
public class ClienteController extends HttpServlet {

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
            out.println("<title>Servlet ClienteController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClienteController at " + request.getContextPath() + "</h1>");
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
        //FUNCION	DESCRIPCION                             URL                                     METODO	VISTA
        //LISTAR	Muestra al inicio todas las clientes	http://localhost:8080/admin/clientes	GET	clientes/index.jsp
        
        //para obtener la ruta que ingresa usamos request.getpahtinfo
        String URL = request.getPathInfo();
        
        if (URL == null || URL.equals("/")) {
            
            //1. obtener las clientes de la base de datos
            AD_Cliente ad_cliente = new AD_Cliente();
            List<Cliente> listado = new ArrayList<Cliente>();
            listado = ad_cliente.getAll();
            
            //2. agregar como atributo al request la lista de clientes
            request.setAttribute("listado", listado);
            
            //3. envio del listado de clientes al front            
            request.getRequestDispatcher("/clientes/index.jsp").forward(request, response);
            
        }
        
        //NUEVO	Muestra un formulario para el registro de una nueva cliente	http://localhost:8080/admin/clientes/nuevo	GET	cliente/nuevo.jsp
        if (URL.equals("/nuevo")) {
            //3. envio del listado de clientes al front            
            request.getRequestDispatcher("/clientes/nuevo.jsp").forward(request, response);
        }
        // /editar/1
        // [editar] y [1]
        
        String accion = URL.substring(1) ; // editar/1
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1"
        
        
        if (ruta[0].equals("editar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Cliente cliente = new Cliente();
            AD_Cliente ad = new AD_Cliente();
            cliente = ad.get(id);
            
            request.setAttribute("cliente", cliente);
            
            request.getRequestDispatcher("/clientes/editar.jsp").forward(request, response);
        }
        
        if (ruta[0].equals("eliminar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Cliente cliente = new Cliente();
            AD_Cliente ad = new AD_Cliente();
            cliente = ad.get(id);
            
            request.setAttribute("cliente", cliente);
            
            request.getRequestDispatcher("/clientes/eliminar.jsp").forward(request, response);
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
        //FUNCION	DESCRIPCION                             URL                                         METODO	VISTA
        //GUARDAR	Insertar un cliente en la bd1	http://localhost:8080/admin/clientes/registrar	POST	clientes/nuevo.jsp
        //ACTUALIZAR	actualizar una cliente en la bd1	http://localhost:8080/admin/clientes/actualizar/{id}	POST	clientes/editar.jsp
        
        //para obtener la ruta que ingresa usamos request.getpahtinfo
        String URL = request.getPathInfo();
        
        if (URL.equals("/registrar")) {
            String dni = request.getParameter("dni");
            String nombres_apellidos = request.getParameter("nombres_apellidos");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            
            
            Cliente cliente = new Cliente();
            cliente.setDni(dni);
            cliente.setNombres_apellidos(nombres_apellidos);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            
            AD_Cliente ad = new AD_Cliente();
            ad.Insertar(cliente);
            
            response.sendRedirect(request.getContextPath() + "/admin/clientes");
            
        }
        
        
        String accion = URL.substring(1) ; // actualizar/1
        String[] ruta = accion.split("/"); // ruta[0] = "actualizar", ruta[1] = "1"
        
        if (ruta[0].equals("actualizar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            String dni = request.getParameter("dni");
            String nombres_apellidos = request.getParameter("nombres_apellidos");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            
            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente.setDni(dni);
            cliente.setNombres_apellidos(nombres_apellidos);
            cliente.setDireccion(direccion);
            cliente.setTelefono(telefono);
            
            AD_Cliente ad = new AD_Cliente();
            ad.Actualizar(cliente);
            
            response.sendRedirect(request.getContextPath() + "/admin/clientes");
            
        }
        
        if (ruta[0].equals("eliminar")) {            
            Integer id = Integer.parseInt(ruta[1]);
            
            AD_Cliente ad = new AD_Cliente();
            ad.Eliminar(id);
            
            response.sendRedirect(request.getContextPath() + "/admin/clientes");            
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
