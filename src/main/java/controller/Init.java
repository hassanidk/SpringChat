package controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Simka
 */
@WebServlet(urlPatterns = {"/Init"})
public class Init extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
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
    	
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("HTML/index.html");
        
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
        response.setContentType("text/html;charset=UTF-8");
        String pseudo = request.getParameter("pseudo");
        String salon = request.getParameter("salon");
        System.out.println(pseudo);
        
        if (pseudo.length() == 0 ){
        	
            response.sendRedirect("HTML/index.html");
        }
        else{
        	
            request.getSession().setAttribute("pseudo", pseudo);
            request.getSession().setAttribute("salon", salon);
            
            /*
             * On crée un cookie utilisateur. 
             * Lors de la création, aucun message n'es mémorisé côté client
             */
            Cookie cookie = new Cookie("utilisateur", "1");
            
            response.addCookie(cookie);
            response.sendRedirect("HTML/interface.html");
            
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
