package fr.univ_lyon1.mif03.chat.controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.mif03.chat.modele.GestionMessages;
import fr.univ_lyon1.mif03.chat.modele.Users;

/**
 *
 * @author Simka
 */
@WebServlet(urlPatterns = {"/Connection"})
public class Init extends HttpServlet {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 * Initialisation de la servlet:
	 * Introduit une map dans le contexte applicatif
	 */
	public void init(ServletConfig sc) throws ServletException{
		super.init(sc);
		Users listeUsers = new Users();
		GestionMessages listeMessages = new GestionMessages();
		sc.getServletContext().setAttribute("users", listeUsers);
		sc.getServletContext().setAttribute("modele", listeMessages);
	
	
	}
	
	
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
    	System.out.println("on est dans le get index");
    	response.setContentType("text/html;charset=UTF-8");
    	request.getRequestDispatcher("HTML/index.html").forward(request, response);
    	
        
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
        System.out.println("goppost");
        boolean inscrit = false;
        Users listeUsers = (Users)getServletContext().getAttribute("users");
		for (String u: listeUsers.getListe()){
			System.out.println(u);
			if (u.equals(pseudo))
				inscrit = true;
		}
	   
        if (pseudo.length() == 0|| inscrit == false  ){
        	//request.getRequestDispatcher("HTML/index.html").forward(request, response);
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
          //  request.getRequestDispatcher("HTML/interface.html").forward(request, response);
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
