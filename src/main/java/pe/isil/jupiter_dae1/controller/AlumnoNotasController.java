/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pe.isil.jupiter_dae1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.isil.jupiter_dae1.dao.AD_AlumnoNotas;
import pe.isil.jupiter_dae1.model.AlumnoNotas;

/**
 *
 * @author RL123
 */
@WebServlet(name = "AlumnoNotasController", urlPatterns = {"/admin/alumnonotas/*"})
public class AlumnoNotasController extends HttpServlet {

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
            out.println("<title>Servlet AlumnoNotasController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnoNotasController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("/alumnonotas/index.jsp").forward(request, response);
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
        
            String accion= URL.substring(1);
            String ruta[] =accion.split("/");
            
            String ep1 = request.getParameter("ep1");
            String ep2 = request.getParameter("ep2");
            String ep3 = request.getParameter("ep3");
            String ep4 = request.getParameter("ep4");
            String ep_parcial = request.getParameter("ep_parcial");
            String ep_final = request.getParameter("ep_final");
            String nombre = request.getParameter("nombre");
            String curso = request.getParameter("curso");
                                  
            ArrayList<AlumnoNotas> notas = new ArrayList<>();
                    
            AlumnoNotas alumnonotas = new AlumnoNotas();
            
            Double promedio_eps;
            Long prom_eps_red;
            
            Double nota;
            Long notas_red;           
                        
            switch (ruta[0]) {
                case "agregar":
   
                    alumnonotas.setEp1(Double.parseDouble(ep1));
                    alumnonotas.setEp2(Double.parseDouble(ep2));
                    alumnonotas.setEp3(Double.parseDouble(ep3));
                    alumnonotas.setEp4(Double.parseDouble(ep4));
                    alumnonotas.setEp_parcial(Double.parseDouble(ep_parcial));
                    alumnonotas.setEp_final(Double.parseDouble(ep_final));
                    
                    
                    
                    promedio_eps = ( Double.parseDouble(ep1) + Double.parseDouble(ep2) + Double.parseDouble(ep3) + Double.parseDouble(ep4)) / 4;
                    prom_eps_red = Math.round(promedio_eps);
                    alumnonotas.setPromedio_eps(prom_eps_red);
                                          
                    nota = (prom_eps_red * 0.4) + (Double.parseDouble(ep_parcial) * 0.3) + (Double.parseDouble(ep_final) * 0.3);
                    notas_red = Math.round(nota);
                    alumnonotas.setNota(notas_red);
                        
                    
                    request.setAttribute("listado", alumnonotas);
                    
                    request.getRequestDispatcher("/alumnonotas/notas.jsp").forward(request, response);
                    
                    break;
                
                   
                case "guardar":
                    AD_AlumnoNotas adalumnonotas = new AD_AlumnoNotas();
                    
                    alumnonotas.setEp1(Double.parseDouble(ep1));
                    alumnonotas.setEp2(Double.parseDouble(ep2));
                    alumnonotas.setEp3(Double.parseDouble(ep3));
                    alumnonotas.setEp4(Double.parseDouble(ep4));
                    alumnonotas.setEp_parcial(Double.parseDouble(ep_parcial));
                    alumnonotas.setEp_final(Double.parseDouble(ep_final));
                    alumnonotas.setAlumno(nombre);
                    alumnonotas.setCurso(curso);
                    
                    
                    promedio_eps = ( Double.parseDouble(ep1) + Double.parseDouble(ep2) + Double.parseDouble(ep3) + Double.parseDouble(ep4)) / 4;
                    prom_eps_red = Math.round(promedio_eps);
                    alumnonotas.setPromedio_eps(prom_eps_red);
                                          
                    nota = (prom_eps_red * 0.4) + (Double.parseDouble(ep_parcial) * 0.3) + (Double.parseDouble(ep_final) * 0.3);
                    notas_red = Math.round(nota);
                    alumnonotas.setNota(notas_red);
                    
                    
                    //insertando... 
                    Boolean resultado = false;
                    
                    
                    if(nombre.trim() == "" || curso.trim() == ""){                       
                        request.getRequestDispatcher("/alumnonotas/index.jsp").forward(request, response);
                    }
                    else{
                        try {
                            resultado = adalumnonotas.Insertar(alumnonotas);
                        } catch (Exception ex) {
                            Logger.getLogger(OrdenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                    
                    
                    break;

                case "cancelar":
                    response.sendRedirect(request.getContextPath() + "");
                    break;

                default:
                    throw new AssertionError();
               
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
