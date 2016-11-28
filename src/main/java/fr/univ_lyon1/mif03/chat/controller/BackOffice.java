package fr.univ_lyon1.mif03.chat.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.univ_lyon1.mif03.chat.modele.GestionMessages;
import fr.univ_lyon1.mif03.chat.modele.Message;
import fr.univ_lyon1.mif03.chat.modele.Users;



@Controller

public class BackOffice {
	@RequestMapping(value ={"/back-office"}, method = RequestMethod.GET)
	public ModelAndView indexBackOffice(){
		ModelAndView model = new ModelAndView("/JSP/BackOffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		// On récupere la liste des salon et on l'affecte au modele
		model.addObject("listeSalon",gestionMessage.getAllSalon());
		return model;
	}
	@RequestMapping(value={"/","index"	}, method = RequestMethod.GET)
	public String index(){
		return "Connection";
		
	}
	
	@Autowired
    ServletContext context; 
	@RequestMapping(value = {"/back-office/salon/{nomsalon}"}, method = RequestMethod.GET)
	public ModelAndView getMessageBySalon(@PathVariable String nomsalon){
		ModelAndView model = new ModelAndView("MessagesSalon");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessages=(ArrayList<Message>) gestionMessage.getMessages(nomsalon);
		if (listeMessages!=null)
			model.addObject("messages",listeMessages);
  		model.setViewName("MessagesSalon");
   	 
   	 return model;
	}
	
	
	@RequestMapping(value = {"/back-office/users"}, method = RequestMethod.POST)
	public String setUserManager(@RequestParam("user")String user){
		Users listeUsers = (Users) context.getAttribute("users");
		listeUsers.addUser(user);
		return "redirect:/back-office";
		
	}
	
	@RequestMapping(value = {"/back-office/salon/{nomsalon}/{idmessage}"}, method = RequestMethod.GET)
	public ModelAndView getMessageById(@PathVariable String nomsalon, int idmessage){
		ModelAndView model = new ModelAndView("MessagesSalon");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		String message = gestionMessage.getMessage(nomsalon, idmessage);
		model.addObject("message",message);
  		model.setViewName("MessagesSalon");
   	 
  		return model;
		
	}
	
	
	
	

}
