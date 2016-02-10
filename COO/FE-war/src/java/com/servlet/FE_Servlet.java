package com.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Russo , Didomenico e Delpopolo
 */
public class FE_Servlet extends HttpServlet {
    
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
        
        
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        
        //Qua si fa con un Session bean che lo va a veridicare nel database
        if((uname.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) ||
           (uname.equalsIgnoreCase("leandro") && password.equalsIgnoreCase("leandro"))||
           (uname.equalsIgnoreCase("nicola") && password.equalsIgnoreCase("nicola"))||
           (uname.equalsIgnoreCase("salvatore") && password.equalsIgnoreCase("salvatore"))||
           (uname.equalsIgnoreCase("daniele") && password.equalsIgnoreCase("daniele"))||
           (uname.equalsIgnoreCase("antonella") && password.equalsIgnoreCase("antonella"))||
           (uname.equalsIgnoreCase("admin") && password.equalsIgnoreCase("zito.2016")))
        {
            HttpSession session=request.getSession();
            session.setAttribute("uname",uname);
            RequestDispatcher rd=request.getRequestDispatcher("logged.jsp");
            rd.forward(request,response);
        }
        else{
             RequestDispatcher rd=request.getRequestDispatcher("index.jsp?login=failed");
             rd.forward(request,response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
