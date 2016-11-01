package controller;
import modele.Message;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





@WebServlet( urlPatterns = {"/Controller"})
public class ControllerMessage extends HttpServlet {
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
		Map<String, List<Message>> listeMessages = new HashMap<String, List<Message>>();
		sc.getServletContext().setAttribute("modele", listeMessages);
	
	
	}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

			// Récupération de la map dans le contexte applicatif
			ServletContext ctx=getServletContext();  
			Map<String, List<Message>> listeMessages = (Map<String, List<Message>> )ctx.getAttribute("modele");
			
			// Déclaration et définition de variables
			String salon = (String) request.getSession().getAttribute("salon");
			String nb_message = String.valueOf(listeMessages.get(salon).size());
			
			//Récupération du cookie utilisateur
			Cookie cookie = getCookie(request, "utilisateur");
			if (!cookie.getValue().equals(nb_message)){
				//request.setAttribute("modele", listeMessages);
				
				cookie.setValue(nb_message);
				response.addCookie(cookie);
							
				request.getRequestDispatcher("JSP/Messages.jsp").forward(request, response);
			}else{
				// Page non modifiée, envoi de l'entête de code 204
		        response.setStatus(204);       
			}
	
	}
	
	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
		 request.getRequestDispatcher("JSP/Stockage.jsp").forward(request, response); 	
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
	    
	    
	    /**
	     * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	     * depuis la requête HTTP. Méthode provenant d'OpenClassRoom
	     */
	    
	    public Cookie getCookie( HttpServletRequest request, String nom ) {
	        Cookie[] cookies = request.getCookies();
	        if ( cookies != null ) {
	            for ( Cookie cookie : cookies ) {
	                if (cookie != null && nom.equals( cookie.getName())) 
	                    return cookie;
	            }
	        }
	        return null;
	    }

	}
