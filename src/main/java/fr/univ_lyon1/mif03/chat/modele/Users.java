package fr.univ_lyon1.mif03.chat.modele;

import java.util.ArrayList;

public class Users {
	static private ArrayList<String> listePseudo;
	
	// Constructeur par défaut
	public Users(){
		listePseudo = new ArrayList<String>();
		// ON rajoute au moins un utilisateur 
		listePseudo.add("admin"); 
	}
	
	public void addUser(String pseudo){
		listePseudo.add(pseudo);
		
	}
	
	public void removeUser(String pseudo){
		listePseudo.remove(pseudo);
	}
	
	public ArrayList<String> getListe(){
		return listePseudo;
	}
	
	
	

}
