package fr.devavance.calculatrice.controller;

/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import fr.devavance.calculatrice.metiers.Operation;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.devavance.calculatrice.metiers.Calculator;
import fr.devavance.calculatrice.metiers.Operation;


/**
 *
 * @author B. LEMAIRE
 * Controller pour la calculatrice
 */
 

@WebServlet(urlPatterns = {"/calculate/*"})
public class CalculatorController extends HttpServlet {
    
    //<editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Operation operation = new Operation();
        
        extractParamsFromURLParamaters(request, operation);
      
        checkIfAllElementsOfOperationNotEmpty(operation);
        
        proceedOperation(operation);
        
        proceedView(request, response, operation);
     

    }
    
    
    private void extractParamsFromURLParamaters(HttpServletRequest request, 
            Operation operation){
        
        String operator= request.getParameter("operation");
        String operande1= request.getParameter("operande1");
        String operande2= request.getParameter("operande2");
        
        operation.setOperator(operator);
        operation.setOperande1(operande1);
        operation.setOperande2(operande2);
    }
    
    
    private void checkIfAllElementsOfOperationNotEmpty(Operation operation) 
            throws ServletException{
        
        String operator= operation.getOperator();
        String operande1= operation.getOperande1();
        String operande2= operation.getOperande2();
                         
        if (operator==null 
            || operator.isEmpty() 
            || operande1 == null
            || operande1.isEmpty()
            || operande2 == null 
            || operande2.isEmpty()
         ) 
        throw new ServletException("Format de l'opération invalide !");
        
    }
    
    
    
     private void proceedOperation(Operation operation) 
            throws ServletException{
            
        String operator= operation.getOperator();
        String operande1= operation.getOperande1();
        String operande2= operation.getOperande2();
        
        double result;
            
        
         try {            
            if (operator.equals("add"))
                result = Calculator.addition(operande1, 
                        operande2);
            else if (operator.equals("sub"))
                result = Calculator.soustraction(operande1, 
                        operande2);
            else if (operator.equals("div"))
                result = Calculator.division(operande1, 
                        operande2);
            else if (operator.equals("mul"))
                result = Calculator.multiplication(operande1, 
                        operande2);
            else throw new Exception("Opération invalide !");
        }
        catch(ArithmeticException e ){
            throw new ServletException(e);
        }
        catch (NumberFormatException e) {
               throw new ServletException(e);
        }
        catch (Exception e) {
               throw new ServletException(e);
        }       
   
        operation.setResult(result);
        
    }
     
    public void proceedView(HttpServletRequest request, HttpServletResponse response, Operation operation) 
            throws IOException{
        
           
        String operator= operation.getOperator();
        String operande1= operation.getOperande1();
        String operande2= operation.getOperande2();
        double result = operation.getResult();
        
         // Construction dynamique de l'URL
        String scheme = request.getScheme(); // http ou https
        String serverName = request.getServerName(); // nom de domaine ou IP
        int serverPort = request.getServerPort(); // port (ex: 8080)
        String contextPath = "/calculatrice_frontend"; // /calculatrice_frontend (si c'est là que tu es)

        String homeURL = scheme + "://" + serverName + ":" + serverPort + contextPath + "/";
               
        response.setContentType("text/html;charset=UTF-8");
        
                PrintWriter out = response.getWriter();
        try {

       
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Calculator</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div>");
        out.println("<p class=\"operande\">Operande 1 : " + operande1+"</p>");
        out.println("<p class=\"operande\">Operande 2 : " + operande2+"</p>");
        out.println("<p class=\"operation\">Operateur : " + operator+"</p>");
        out.println("<p class=\"resultat\">resultat : " + result +"</p>");
        out.println("</div>");
        
        out.println("<a href=\"" + homeURL + "\">Home Page</a>");
      
        out.println("</body>");
        out.println("</html>");
        }
        finally{
        out.close();
        }
    }
   

}
