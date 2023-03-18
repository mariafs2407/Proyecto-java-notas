/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import pe.isil.jupiter_dae1.dao.AD_Producto;
import pe.isil.jupiter_dae1.model.Producto;
/**
 *
 * @author maria
 */
@MultipartConfig
@WebServlet(name = "ProductoController", urlPatterns = {"/admin/productos/*"})
public class ProductoController extends HttpServlet {

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
            out.println("<title>Servlet ProductoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductoController at " + request.getContextPath() + "</h1>");
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
        //para obtener la ruta que ingresa usamos request.getPathinfo
        String URL = request.getPathInfo();//caputo la ruta
       if(URL==null || URL.equals("/")){
           //1.obtener los productos de la bd
           AD_Producto ad_producto= new AD_Producto();
           List<Producto> listado= new ArrayList<Producto>();
           listado=ad_producto.getAll();
           
           //2.Agregar como atributo el request 
           request.setAttribute("listado",listado);
           
           //3.Enviar este listado al framework
           request.getRequestDispatcher("/productos/index.jsp").forward(request, response);
       }
       
       //NUEVO	Muestra un formulario para el registro de una nueva producto	GET	
        if (URL.equals("/nuevo")) {
            //3. envio del listado        
            request.getRequestDispatcher("/productos/nuevo.jsp").forward(request, response);
        }
        
        // /editar/1
        // [editar] y [1]
        
        String accion = URL.substring(1) ; // editar/1
        String[] ruta = accion.split("/"); // ruta[0] = "editar", ruta[1] = "1"
        
        
        if (ruta[0].equals("editar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Producto producto = new Producto();
            AD_Producto ad = new AD_Producto();
            producto = ad.get(id);
            
            request.setAttribute("producto", producto);
            
            request.getRequestDispatcher("/productos/editar.jsp").forward(request, response);
        }
        
        if (ruta[0].equals("eliminar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            Producto producto = new Producto();
            AD_Producto ad = new AD_Producto();
            producto = ad.get(id);
            
            request.setAttribute("producto", producto);
            
            request.getRequestDispatcher("/productos/eliminar.jsp").forward(request, response);
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
        //para obtener la ruta que ingresa usamos request.getpahtinfo
        String URL = request.getPathInfo();
        Producto producto= new Producto();
        AD_Producto ad= new AD_Producto();
        
        if (URL.equals("/registrar")) {
          String categoria_id = request.getParameter("categoria_id");
          String nombre= request.getParameter("nombre");          
          String precio= request.getParameter("precio");
          String stock = request.getParameter("stock");
          Part part =request.getPart("imagen");   
          InputStream inputStream=part.getInputStream();
            
          producto= new Producto();
           
          producto.setCategoria_id(Integer.parseInt(categoria_id));
          producto.setNombre(nombre);
          producto.setImagen(inputStream);
          producto.setPrecio(Double.parseDouble(precio));
          producto.setStock(Integer.parseInt(stock));
            
          ad= new AD_Producto();
          ad.Insertar(producto);     
          
            
          response.sendRedirect(request.getContextPath() + "/admin/productos");
        }
       
        String accion = URL.substring(1) ; // actualizar/1
        String[] ruta = accion.split("/"); // ruta[0] = "actualizar", ruta[1] = "1"
        
        if (ruta[0].equals("actualizar")) {
            
            Integer id = Integer.parseInt(ruta[1]);
            
            String categoria_id = request.getParameter("categoria_id");
            String nombre= request.getParameter("nombre");            
            String precio= request.getParameter("precio");
            String stock = request.getParameter("stock");
            Part part =request.getPart("imagen");   
            InputStream inputStream=part.getInputStream();
            
            
            producto= new Producto();
            producto.setId(id);
            producto.setCategoria_id(Integer.parseInt(categoria_id));
            producto.setNombre(nombre);
            producto.setImagen(inputStream);
            producto.setPrecio(Double.parseDouble(precio));
            producto.setStock(Integer.parseInt(stock));
            
            AD_Producto ad_producto= new AD_Producto();
            ad_producto.Actualizar(producto);
           
            response.sendRedirect(request.getContextPath() + "/admin/productos");
            
        }
        
         
        
        if (ruta[0].equals("eliminar")) {            
            Integer id = Integer.parseInt(ruta[1]);
            
            ad = new AD_Producto();
            ad.Eliminar(id);
            
            response.sendRedirect(request.getContextPath() + "/admin/productos");            
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
