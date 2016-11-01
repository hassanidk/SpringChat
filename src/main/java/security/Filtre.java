package security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Filtre de sécurité pour toutes les pages
@WebFilter("/*")
public class Filtre implements Filter {

	private ServletContext context;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	//	this.context = filterConfig.getServletContext();
	}
	
		
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
	        if (session == null ){
	        	
	        	/* Cas où l'utilisateur est sur la page de login*/
	        	if (req.getRequestURI().contains("index") || req.getRequestURI().equals("/my-webapp/") || req.getRequestURI().contains("Init")){
	        		chain.doFilter(request, response);
	        	}else{
	        		System.out.println(req.getRequestURI());
	        		res.sendRedirect("/my-webapp");
	        	}
	        		
	        }else{
	        	// Cas où l'utilisateur est connecté, et qu'il tente d'acceder à la page de login
	        	if (req.getRequestURI().contains("index")){
	        		res.sendRedirect("/my-webapp");
	        	}else{
	        		chain.doFilter(request, response);
	        	}
	        	
	        }
       
	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}



}
