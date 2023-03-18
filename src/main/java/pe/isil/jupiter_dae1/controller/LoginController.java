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
import org.jasypt.util.password.StrongPasswordEncryptor;
import pe.isil.jupiter_dae1.dao.AD_Usuario;
import pe.isil.jupiter_dae1.model.Usuario;

/**
 *
 * @author maria
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
        //INICIO DE SESION
        //PASO1:Capturar o recibir el email y el password desde la vista
        String email=request.getParameter("email");
        String password= request.getParameter("password");
        //01.01 validar entradas, ejemplo que el correo sea un correo
        //PASO2:Validar que el usuario exista y la contraseña
        AD_Usuario ad= new AD_Usuario();
        Usuario usuario= ad.login(email);
        
        if(usuario !=null){//si encontramos el usuario con el correo login
            //comparar la contraseña enviada desde el form con la contraseña de la BD
            StrongPasswordEncryptor encriptador= new StrongPasswordEncryptor();
            if(encriptador.checkPassword(password,usuario.getPassword())){
                HttpSession sesion= request.getSession(true);
                //voy a guardar los datos del usuario en la sesion
                sesion.setAttribute("usuario_logueado",usuario);
                response.sendRedirect(request.getContextPath()+"/admin");
            }else{
                //crear un mensaje del tipo flash
                HttpSession sesion= request.getSession(true);
                sesion.setAttribute("mensaje_error","Datos incorrectos,la contraseña es incorrecta");
                response.sendRedirect(request.getContextPath()+"/login");
            }
            
        }else{
            HttpSession sesion= request.getSession(true);
            sesion.setAttribute("mensaje_error","Dato incorrectos, o meail no existe");
            response.sendRedirect(request.getContextPath() + "/login");
        }
        //PASO3: Si el usuario existe ,ingresa,sino regresarlo a login
        
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
