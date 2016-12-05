package fr.univ_lyon1.mif03.chat.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.mif03.chat.modele.GestionMessages;

@WebServlet(urlPatterns = { "/Controller" })
public class ControllerMessage extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 * Initialisation de la servlet: Introduit une map dans le contexte
	 * applicatif
	 */
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de GestionMessage dans le contexte applicatif
		ServletContext ctx = getServletContext();
		GestionMessages listeMessages = (GestionMessages) ctx.getAttribute("modele");

		// Déclaration et définition de variables
		String salon = (String) request.getSession().getAttribute("salon");
		String nb_message = String.valueOf(listeMessages.getNbMessages(salon));

		// Récupération du cookie utilisateur
		Cookie cookie = getCookie(request, "utilisateur");

		// Si le nombre de message est différents du cookie utilisateur, la page
		// est modifiée
		if (!cookie.getValue().equals(nb_message)) {
			cookie.setValue(nb_message);
			response.addCookie(cookie);
			request.getRequestDispatcher("JSP/Messages.jsp").forward(request, response);

		} else {
			// Page non modifiée, envoi de l'entête de code 304
			response.setStatus(304);

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

	public Cookie getCookie(HttpServletRequest request, String nom) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && nom.equals(cookie.getName()))
					return cookie;
			}
		}
		return null;
	}

}
