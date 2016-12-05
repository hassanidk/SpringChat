package fr.univ_lyon1.mif03.chat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.univ_lyon1.mif03.chat.modele.GestionMessages;
import fr.univ_lyon1.mif03.chat.modele.Message;
import fr.univ_lyon1.mif03.chat.modele.Users;

@Controller
/**
 * Controller Spring Utilisé pour construire le back-office
 * 
 * @author Kassim
 *
 */
public class BackOffice {
	// Récupération du contexte de l'application
	@Autowired
	ServletContext context;

	// ------- Redirige vers l'index de l'application
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "Init";

	}

	/*********** ACCUEIL BACK-OFFICE **************/
	/*
	 * Rédirige vers l'accueil du back office
	 * Contient:
	 * 		- Un lien vers la gestion utilisateur
	 * 		- Un lien vers la gestion des salons
	 * 		- Un lien vers la gestion des messages
	 */
	@RequestMapping(value = { "/back-office" }, method = RequestMethod.GET)
	public String indexBackOffice(HttpServletRequest request) {
		return "JSP/home_backoffice.jsp";

	}

	/********************* GESTION SALON **********************/
	/*
	 * Interface d'accueil de la gestion de salon
	 * Possibilités :
	 * 		- Avoir la liste des messages par salon, ainsi que le nombre de message par salon
	 * 		- Avoir le contenu d'un message en particulier en fonction du salon et de l'id du message
	 * 		- Récuperer tous les messages d'un salon suivant un certain id
	 * 		- Suppression de salon
	 * 		- Suppression du dernier message d'un salon
	 */
	@RequestMapping(value = { "/back-office/salon" }, method = RequestMethod.GET)
	public ModelAndView getInterfaceGestionSalon() {
		ModelAndView model = new ModelAndView("/JSP/salon_backoffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		// On récupere la liste des salon et on l'affecte au modele
		model.addObject("listeSalon", gestionMessage.getAllSalon());

		return model;
	}

	// -- Permet de recuperer un message en fonction du nom de salon et de l'id
	@RequestMapping(value = { "/back-office/salon*" }, method = RequestMethod.GET)
	public ModelAndView getMessageById(@RequestParam String nomsalon, @RequestParam int idmessage) {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		model.addObject("message", gestionMessage.getMessage(nomsalon, idmessage)); 
		return model;
	}

	// Récupérer tous les messages envoyés après un message donné, dont l'id sera passé en paramètre
	@RequestMapping(value = {"/back-office/salon$"}, method = RequestMethod.GET)
	public ModelAndView getAllMessageAfterId(@RequestParam String nomsalon, @RequestParam int idmessage) {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessages=(ArrayList<Message>) gestionMessage.getMessages(nomsalon);
		if (listeMessages == null)
			return model;
		ArrayList<Message> listeTemp = new ArrayList<Message>(listeMessages);
		if (idmessage > listeTemp.size())
			return model;
		for (int i =0;i <idmessage;i++) 
			listeTemp.remove(i);
		model.addObject("messages_id",listeTemp);
		return model;
	}
	// Récupere la liste de tous les messages d'un salon ainsi que le
	// nombre de message de celui ci
	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.GET)
	public ModelAndView getAllMessagesBySalon(@PathVariable String nomsalon) {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessages = (ArrayList<Message>) gestionMessage.getMessages(nomsalon);
		int nbMessages = gestionMessage.getNbMessages(nomsalon);
		if (listeMessages != null) {
			model.addObject("messages", listeMessages);
			model.addObject("nbmessages", nbMessages);
		}
		

		return model;
	}
	// -- AJOUT MESSAGE
	@RequestMapping(value = { "/back-office/salon" }, method = RequestMethod.POST)
	public ModelAndView addMessage(@RequestParam String nomSalon, @RequestParam String message) {
		ModelAndView model = new ModelAndView("/JSP/salon_backoffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		try{
			gestionMessage.getSalon(nomSalon).add(new Message("admin", message));
			model.addObject("listeSalon", gestionMessage.getAllSalon());
		}catch(Exception e){
			model.addObject("erreur", "Le salon n'existe pas");
		}
		
		return model;
	}

	// -- SUPPRESSION SALON --
	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.DELETE)
	public ModelAndView delMessageBYSalon(@PathVariable String nomsalon) {
		ModelAndView model = new ModelAndView("/JSP/salon_backoffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		gestionMessage.removeSalon(nomsalon);
		model.addObject("listeSalon", gestionMessage.getAllSalon());
		return model;
	}

	
  // SUPPRESSION DU DERNIER MESSAGE D'UN SALON
  
	  @RequestMapping(value = {"/back-office/salon/{nomsalon}*"}, method = RequestMethod.DELETE) 
	  public ModelAndView removeLastMessage(@PathVariable String nomsalon){ 
		  ModelAndView model = new ModelAndView("blablabla");
		  GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele"); 
		  int lastMessage = gestionMessage.getNbMessages(nomsalon); 
		  if (lastMessage >0)
			  gestionMessage.getSalon(nomsalon).remove(lastMessage-1); 
		  return model; 
		  }
	 
	

	// MODIFICATION DU DERNIER MESSAGE D'UN SALON
	/**
	 * @param nomsalon
	 * @param message
	 * @return
	 */
	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.PUT)
	public ModelAndView setNewLastMessage(@PathVariable String nomsalon, @RequestParam("message") String message) {
		ModelAndView model = new ModelAndView("blablabla");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		int lastMessage = gestionMessage.getNbMessages(nomsalon);
		if (lastMessage > 0)
			gestionMessage.getSalon(nomsalon).get(lastMessage).setMessage(message);
		return model;
	}

	/********************* GESTION UTILISATEUR **********************/
	/*
	 * Page d'accueil de la gestion d'utilisateur. Contient : - La liste des
	 * membres du chat - Un formulaire d'ajout de membre Permet : - La
	 * modification des pseudos utilisateur - Récuperer le pseudo et la liste
	 * des salons auxquel il a participé
	 */
	@RequestMapping(value = { "/back-office/users" }, method = RequestMethod.GET)
	public ModelAndView getInterfaceUsers() {
		ModelAndView model = new ModelAndView("/JSP/utilisateurs_backoffice.jsp");
		Users listeUsers = (Users) context.getAttribute("users");
		if (listeUsers != null)
			model.addObject("listeUsers", listeUsers.getListe());

		return model;
	}

	// PERMET D'AJOUTER UN UTILISATEUR AU CHAT
	@RequestMapping(value = { "/back-office/users" }, method = RequestMethod.POST)
	public ModelAndView setUserManager(@RequestParam("user") String user) {
		ModelAndView model = new ModelAndView("/JSP/utilisateurs_backoffice.jsp");
		Users listeUsers = (Users) context.getAttribute("users");
		listeUsers.addUser(user);
		model.addObject("listeUsers", listeUsers.getListe());
		// MODIFIER LA REDIRECTION (MESSAGE DE CONFIRMATION PUIS REDIRECT )
		return model;

	}

	// RECUPERE LA LISTE DES SALONS OÙ L'UTILISATEUR A POSTÉ UN MESSAGE
	@RequestMapping(value = { "/back-office/users/{id}" }, method = RequestMethod.GET)
	public ModelAndView getAllSalonByUser(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		// On recupere la liste de tous les messages
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		// On récupere la liste de tous les utilisateurs
		Users listeUsers = (Users) context.getAttribute("users");
		// On récupere le pseudo utilisateur via l'id dans la liste utilisateur
		String pseudo = listeUsers.getUser(id);
		List<String> listeSalonByUser = (ArrayList<String>) gestionMessage.getAllSalonByUser(pseudo);

		if (listeSalonByUser != null) {
			model.addObject("listeSalon", listeSalonByUser);
		}
		return model;
	}

	// MODIFICATION DU PSEUDO D'UN UTILISATEUR
	@RequestMapping(value = { "/back-office/users/{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<String> renamePseudo(@PathVariable("id") int id, @RequestBody String newpseudo) {
		Users listeUsers = (Users) context.getAttribute("users");
		if (listeUsers.getUser(id) == null)
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		listeUsers.renameUser(id, newpseudo);
		return new ResponseEntity<String>(newpseudo, HttpStatus.OK);
	}

}
