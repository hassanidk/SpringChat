package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modele.Message;

@WebServlet( urlPatterns = {"/Controller"})
public class ControllerMessage extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private Map<String, List<Message>> listeMessages = new HashMap<String, List<Message>>();
	
	
	@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		request.setAttribute("modele", listeMessages);
		request.getRequestDispatcher("JSP/Messages.jsp").forward(request, response);
	        
	    }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	String nomSalon;
		    boolean testSalon;
		    testSalon = false;
		    nomSalon = (String)request.getSession().getAttribute("salon");
		    
		    	
		    Message mes = new Message((String)request.getSession().getAttribute("pseudo"), request.getParameter("newMessage"));
	        for (Map.Entry<String, List<Message>> it: listeMessages.entrySet()){
	            if (it.getKey().equals(nomSalon)){
	                testSalon = true;
	            }		  		
	        }
	        // Création d'une arraylist de message si le salon n'existe pas
	        if (testSalon == false){
	            listeMessages.put(nomSalon, new ArrayList<Message>());
	        }	
	        // Ajout du message dans la liste de message concerné
	        listeMessages.get(nomSalon).add(mes);
	     
	     request.setAttribute("modele", listeMessages);
		 request.getRequestDispatcher("JSP/Messages.jsp").forward(request, response);
		 
		 
		
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
