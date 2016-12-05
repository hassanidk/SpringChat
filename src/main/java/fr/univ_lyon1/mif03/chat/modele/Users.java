package fr.univ_lyon1.mif03.chat.modele;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="utilisateurs")
public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static private ArrayList<String> listePseudo;
	
	// Constructeur par défaut
	public Users(){
		listePseudo = new ArrayList<String>();
		// ON rajoute au moins un utilisateur 
		listePseudo.add("admin"); 
	}
	
	// Ajout utilisateur
	
	public void addUser(String pseudo){
		listePseudo.add(pseudo);
		
	}
	
	// Suppression utilisateur
	public void removeUser(String pseudo){
		listePseudo.remove(pseudo);
	}
	
	// Renome un utilisateur
	public void renameUser(int id, String pseudo){
		listePseudo.set(id, pseudo);
	}
	
	// Retourne la liste des pseudos
	@XmlElement(name="utilisateur")
	public ArrayList<String> getListe(){
		return listePseudo;
	}
	
	/*
	 * Permet d'avoir le pseudo via l'id
	 * On recupere le pseudo via la position au sein de la liste
	 */
	public String getUser(int id){
		return listePseudo.get(id);
	}
	
	
	

}
