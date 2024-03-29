package fr.univ_lyon1.mif03.chat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.univ_lyon1.mif03.chat.exception.CustomException;
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
		return "HTML/index.html";

	}

	/*********** ACCUEIL BACK-OFFICE **************/
	/*
	 * Redirige vers l'accueil du back office Contient: - Un lien vers la
	 * gestion utilisateur - Un lien vers la gestion des salons - Un lien vers
	 * la gestion des messages
	 */
	@RequestMapping(value = { "/back-office" }, method = RequestMethod.GET)
	public String indexBackOffice(HttpServletRequest request) {
		return "JSP/home_backoffice.jsp";

	}

	/********************* GESTION SALON **********************/
	/*
	 * Interface d'accueil de la gestion de salon Possibilités : - Avoir la
	 * liste des messages par salon, ainsi que le nombre de message par salon -
	 * Avoir le contenu d'un message en particulier en fonction du salon et de
	 * l'id du message - Récuperer tous les messages d'un salon suivant un
	 * certain id - Suppression de salon - Suppression du dernier message d'un
	 * salon
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
	public ModelAndView getMessageById(@RequestParam String nomsalon, @RequestParam int idmessage)
			throws CustomException {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		String message = gestionMessage.getMessage(nomsalon, idmessage);
		if (message.equals(""))
			throw new CustomException("Message inexistant");
		model.addObject("message", message);
		return model;
	}

	// Récupérer tous les messages envoyés après un message donné, dont l'id
	// sera passé en paramètre
	@RequestMapping(value = { "/back-office/salon$" }, method = RequestMethod.GET)
	public ModelAndView getAllMessageAfterId(@RequestParam String nomsalon, @RequestParam int idmessage)
			throws CustomException {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessages = (ArrayList<Message>) gestionMessage.getMessages(nomsalon);

		if (listeMessages == null)
			throw new CustomException("Salon inexistant");
		ArrayList<Message> listeTemp = new ArrayList<Message>(listeMessages);
		if (idmessage > listeTemp.size())
			throw new CustomException("L'id de message est inexistant");
		for (int i = 0; i < idmessage; i++)
			listeTemp.remove(i);
		model.addObject("messages_id", listeTemp);
		return model;
	}
	// Récupere la liste de tous les messages d'un salon ainsi que le
	// nombre de message do celui ci

	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.GET)
	public ModelAndView getAllMessagesBySalon(@PathVariable String nomsalon) throws CustomException {
		ModelAndView model = new ModelAndView("/JSP/MessagesSalon.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessages = (ArrayList<Message>) gestionMessage.getMessages(nomsalon);
		//int nbMessages = gestionMessage.getNbMessages(nomsalon);
		if (listeMessages != null) {

			model.addObject("messages", listeMessages);
	//		model.addObject("nbmessages", nbMessages);

		} else {
			throw new CustomException("Salon inexistant");
		}

		return model;
	}

	// -- AJOUT MESSAGE DANS UN SALON
	@RequestMapping(value = { "/back-office/salon" }, method = RequestMethod.POST)
	public ModelAndView addMessage(@RequestParam String nomSalon, @RequestParam String message) throws CustomException {
		ModelAndView model = new ModelAndView("/JSP/salon_backoffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		if (gestionMessage.getSalon(nomSalon) != null) {
			gestionMessage.getSalon(nomSalon).add(new Message("admin", message));
			model.addObject("listeSalon", gestionMessage.getAllSalon());
		} else {
			// On diffuse l'exception dans le cas où le salon n'existe pas
			throw new CustomException("Salon inexistant");
		}

		return model;
	}

	// -- SUPPRESSION SALON --
	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.DELETE)
	public ModelAndView delMessageBYSalon(@PathVariable String nomsalon) {
		ModelAndView model = new ModelAndView("/JSP/salon_backoffice.jsp");
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		// LE cas où le salon n'existe pas est géré côté serveur
		gestionMessage.removeSalon(nomsalon);
		model.addObject("listeSalon", gestionMessage.getAllSalon());
		return model;
	}

	// SUPPRESSION DU DERNIER MESSAGE D'UN SALON

	@RequestMapping(value = { "/back-office/salon/edit/{nomsalon}" }, method = RequestMethod.DELETE)
	public ResponseEntity<Integer> removeLastMessage(@PathVariable String nomsalon) {

		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		int lastMessage = gestionMessage.getNbMessages(nomsalon);

		if (lastMessage > 0) {
			gestionMessage.getSalon(nomsalon).remove(lastMessage);
			return new ResponseEntity<Integer>(lastMessage, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
	}

	// MODIFICATION DU DERNIER MESSAGE D'UN SALON
	/**
	 * @param nomsalon
	 * @param message
	 * @return
	 */
	@RequestMapping(value = { "/back-office/salon/{nomsalon}" }, method = RequestMethod.PUT)
	public ResponseEntity<String> setNewLastMessage(@PathVariable String nomsalon, @RequestBody String message) {
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		int lastMessage = gestionMessage.getNbMessages(nomsalon);
		if (lastMessage > 0) {
			gestionMessage.getSalon(nomsalon).get(lastMessage).setMessage(message);
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

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
		// Pas d'exception si la liste d'utilisateur est nulle..
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
		} // On ne déclenche pas d'erreur dans le cas échéant
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
	
	/**
	 * PARTIE AJAX
	 * 
	 */
	// NOTE : Le controlleur Spring fais le mapping sur chaque URI
	// Nous sommes obligés de faire un mapping .
	@RequestMapping(value = {"/chat*"})
	public String homechat(){
		return "HTML/chat.html";
	}
	@RequestMapping(value = {"/chat/salon"}, method = RequestMethod.GET)
	public @ResponseBody String getSalon(){
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		JSONObject jobjroot = new JSONObject();
		JSONArray jarr = new JSONArray();
		for (String salon : gestionMessage.getAllSalon()){
			JSONObject jobj = new JSONObject();
			jobj.put("salon", salon);
			jarr.put(jobj);
		}
		jobjroot.put("salons", jarr);
		return jobjroot.toString();
	}
	// Création salon
	@RequestMapping(value = {"/chat/salon"}, method = RequestMethod.POST)
	public @ResponseBody String setSalon(@RequestParam String nomsalon){
		
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		JSONObject jobjroot = new JSONObject();
		if (gestionMessage.getSalon(nomsalon) ==null){
			gestionMessage.setSalon(nomsalon);
			jobjroot.put("message", "Salon créé");
		}else{
			jobjroot.put("message", "Salon déjà existant");
		}
		
		return jobjroot.toString();
	}
	// Recupere la liste des messages du salon
	// NOTE : Faire en sorte de recuperer que les messages non dispo coté client
	@RequestMapping(value = {"/chat/salon/{salon}"}, method = RequestMethod.GET)
	public @ResponseBody String getMessage(@PathVariable("salon")String salon, @RequestParam int idmessage){
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessage = (ArrayList<Message>)gestionMessage.getSalon(salon);
		JSONObject jobjroot = new JSONObject();
		jobjroot.put("salon", salon);
		if (listeMessage ==null || listeMessage.size()==idmessage){
			return "";//jobjroot.toString();
		}else{
			JSONArray jarr = new JSONArray();
			int index = 0;
			for (Message m : listeMessage){
				index++;
				if (index >idmessage){
					JSONObject jobj = new JSONObject();
					jobj.put("auteur", m.getPseudo());
					jobj.put("message", m.getMessage());
					
					jarr.put(jobj);
				}
			
		}
		jobjroot.put("messages", jarr);
		jobjroot.put("nbmessage", gestionMessage.getNbMessages(salon));
		return jobjroot.toString();
		}
	}
	// AJout d'un message dans un salon
	@RequestMapping(value = {"/chat/salon/{salon}"}, method = RequestMethod.POST)
	public @ResponseBody String postMessage(@PathVariable("salon") String salon,  @RequestParam String json){
		
		
		GestionMessages gestionMessage = (GestionMessages) context.getAttribute("modele");
		ArrayList<Message> listeMessage = (ArrayList<Message>)gestionMessage.getSalon(salon);
		// Cas où le salon n'existe pas
		if (listeMessage == null){
			gestionMessage.setSalon(salon);
		}
		
		JSONObject jobj = new JSONObject(json);
		// Cas où le message est vide
		if (jobj.getString("message").equals("")){
			return "";
		}
		Message newMessage = new Message(jobj.getString("auteur"), jobj.getString("message"));
		gestionMessage.getSalon(salon).add(newMessage);
		
		JSONObject jnbmessage = new JSONObject();
		// On retourne le nombre de message ainsi que la liste des message
		
		jnbmessage.put("nbmessage", gestionMessage.getNbMessages(salon));
		return jnbmessage.toString();
	}
	
	
}
