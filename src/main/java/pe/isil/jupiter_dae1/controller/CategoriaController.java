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
import pe.isil.jupiter_dae1.dao.AD_Categoria;
import pe.isil.jupiter_dae1.model.Categoria;

/**
 *
 * @author maria
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/admin/categorias/*"})
public class CategoriaController extends HttpServlet {

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
            out.println("<title>Servlet CategoriaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaController at " + request.getContextPath() + "</h1>");
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
        //LISTAR	Muestra al inicio todas las categorias	http://localhost:8080/admin/categorias	GET	categorias/index.jsp
        
        //para obtener la ruta que ingresa usamos request.getpahtinfo
        String URL = request.getPathInfo();
        
        if (URL == null || URL.equals("/")) {
            
            //1. obtener las categorias de la base de datos
            AD_Categoria ad_categoria = new AD_Categoria();
            List<Categoria> listado = new ArrayList<Categoria>();
            listado = ad_categoria.getAll();
            
            //2. agregar como atributo al request la lista de categorias
            request.setAttribute("listado", listado);
            
            //3. envio del listado de categorias al front            
            request.getRequestDispatcher("/categorias/index.jsp").forward(request, response);
            
        }
        
        //NUEVO	Muestra un formulario para el registro de una nueva categoria	http://localhost:8080/admin/categorias/nuevo	GET	categoria/nuevo.jsp
        if (URL.equals("/nuevo")) {
            //3. envio del listado de categorias al front            
            request.getRequestDispatcher("/categorias/nuevo.jsp").forward(request, response);
        }
        // /editar/1
        // [editar] y [1]
        
        String accion = URL.substring(1) ; // editar/1
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1"
        
        
        if (ruta[0].equals("editar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Categoria categoria = new Categoria();
            AD_Categoria ad = new AD_Categoria();
            categoria = ad.get(id);
            
            request.setAttribute("categoria", categoria);
            
            request.getRequestDispatcher("/categorias/editar.jsp").forward(request, response);
        }
        
        if (ruta[0].equals("eliminar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Categoria categoria = new Categoria();
            AD_Categoria ad = new AD_Categoria();
            categoria = ad.get(id);
            
            request.setAttribute("categoria", categoria);
            
            request.getRequestDispatcher("/categorias/eliminar.jsp").forward(request, response);
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
        //GUARDAR	Insertar un categoria en la bd1	http://localhost:8080/admin/categorias/registrar	POST	categorias/nuevo.jsp
        //ACTUALIZAR	actualizar una categoria en la bd1	http://localhost:8080/admin/categorias/actualizar/{id}	POST	categorias/editar.jsp
        
        //para obtener la ruta que ingresa usamos request.getpahtinfo
        String URL = request.getPathInfo();
        
        if (URL.equals("/registrar")) {
            String nombre = request.getParameter("nombre");
            Categoria categoria = new Categoria();
            categoria.setNombre(nombre);
            
            AD_Categoria ad = new AD_Categoria();
            ad.Insertar(categoria);
            
            response.sendRedirect(request.getContextPath() + "/admin/categorias");
            
        }
        
        
        String accion = URL.substring(1) ; // actualizar/1
        String[] ruta = accion.split("/"); // ruta[0] = "actualizar", ruta[1] = "1"
        
        if (ruta[0].equals("actualizar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            String nombre = request.getParameter("nombre");
            
            Categoria categoria = new Categoria();
            categoria.setId(id);
            categoria.setNombre(nombre);
            
            AD_Categoria ad = new AD_Categoria();
            ad.Actualizar(categoria);
            
            response.sendRedirect(request.getContextPath() + "/admin/categorias");
            
        }
        
        if (ruta[0].equals("eliminar")) {            
            Integer id = Integer.parseInt(ruta[1]);
            
            AD_Categoria ad = new AD_Categoria();
            ad.Eliminar(id);
            
            response.sendRedirect(request.getContextPath() + "/admin/categorias");            
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
