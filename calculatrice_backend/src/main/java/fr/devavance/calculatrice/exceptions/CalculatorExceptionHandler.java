/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.devavance.calculatrice.exceptions;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author laffet
 */
@WebServlet(name = "CalculatorExceptionHandler", urlPatterns = {"/CalculatorExceptionHandler"})
public class CalculatorExceptionHandler extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleException(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleException(request, response);
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        
        if (servletName == null) {
	    servletName = "Unknown";
	  }

        if (throwable instanceof ServletException) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html><head><title>Exception Handler</title></head><body>");
            out.println("<h2>Une exception de type ServletException a été capturée :</h2>");
            out.println("<ul><li><strong>Servlet en cause :</strong> " + servletName + "</li>");
            out.println("<li><strong>Type de l'exception :</strong> " + throwable.getClass().getName() + "</li>");
            out.println("<li><strong>Message de l'exception :</strong> " + throwable.getMessage() + "</li></ul>");
            out.println("<a href=\"http://localhost:8080/calculatrice_frontend/\">Home Page</a>"); 
            out.println("</body></html>");
        } 
        else {
            
            //redirection vers error_backend.html
            response.sendRedirect(request.getContextPath() + "/error_backend.html");
        }
    }
   

}
