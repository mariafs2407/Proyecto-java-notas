/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/ServletListener.java to edit this template
 */
package pe.isil.jupiter_dae1.controller.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author maria
 */
public class AplicacionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent evento) {
        System.out.println("Aplicacion Iniciada"); 
        //se creo una variable global,para poder ver los inicios de sesion login...
        evento.getServletContext().setAttribute("URL_APLICACION", "http://localhost:8080/jupiter_dae1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent evento) {
        System.out.println("Aplicacion Terminada");
    }
}
