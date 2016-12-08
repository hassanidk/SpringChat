package fr.univ_lyon1.mif03.chat.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ_lyon1.mif03.chat.utils.Utils;

@WebServlet(urlPatterns = {"/Logout"})
public class Logout extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.getRequestDispatcher(Utils.prefix+"HTML/index.html").forward(request, response);
	 }
	 
	 
}
