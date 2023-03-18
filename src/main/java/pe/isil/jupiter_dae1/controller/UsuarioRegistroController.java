/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.jasypt.util.password.StrongPasswordEncryptor;
import pe.isil.jupiter_dae1.dao.AD_Usuario;
import pe.isil.jupiter_dae1.model.Usuario;

/**
 *
 * @author maria
 */
@WebServlet(name = "UsuarioRegistroController", urlPatterns = {"/registro_usuario"})
public class UsuarioRegistroController extends HttpServlet {

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
            out.println("<title>Servlet UsuarioRegistroController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioRegistroController at " + request.getContextPath() + "</h1>");
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
        
               request.getRequestDispatcher("/registro_usuario.jsp").forward(request, response);
         
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
            //1.TODO LO CONVERTIMOS EN UTF-8
            request.setCharacterEncoding("UTF-8");
            boolean resultado;
            AD_Usuario ad_usuario= new AD_Usuario();       
            Usuario usuario= new Usuario();      
         
             
            //2.CAPTURAR O OBTENER LOS DATOS DEL FORMULARIO ES DECIR CADA INPUT TIENE UN NAME             
            String email= request.getParameter("email");                              
            String password=request.getParameter("password");   
            String nombre= request.getParameter("nombre");               
               
             //3.CREAR UN OBJ DE CLASE/MODELO USUARIO        
             usuario.setNombre(nombre);
             usuario.setEmail(email);         
             //3.1 ENCRIPTAR EL PASSWORD(CREAR EL OBJ DE CLASE ENCRIPTADO)
             StrongPasswordEncryptor encriptador= new StrongPasswordEncryptor();
             //creo una variable para guardar la contraseña encriptada en el usaurio
             String passwordEncriptado;
             passwordEncriptado= encriptador.encryptPassword(password);
             usuario.setPassword(passwordEncriptado);   
             
             //4. CREAR EL ACCESO A DATOS 
             //esto botara un  booleano 
             resultado = ad_usuario.getValidarPorCorreo(usuario);     
             if( resultado == true){                 
                //ya existe un email     
                 HttpSession sesion= request.getSession(true);
                 sesion.setAttribute("mensaje_error","Error! , El correo ingresado ya existe.");
                 response.sendRedirect(request.getContextPath()+"/registro_usuario");      
             }else{   
                 //no existe un email  
                 ad_usuario.Insertar(usuario);
                 HttpSession sesion= request.getSession(true);
                 sesion.setAttribute("usuario_logueado",usuario);
                 response.sendRedirect(request.getContextPath()+"/login"); 
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
