/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.mif03.chat.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thibom
 */
public class GestionMessages {
    
    static Map<String,List<Message>> listeMessages;
    
    /**
     * Constructeur par défaut
     */
    public GestionMessages(){
        listeMessages = new HashMap<String, List<Message>>();
    }
    
    /**
     * Defini le salon courant pour l'instance
     * @param salon le nom du salon
     */
    public void setSalon(String salon){
    	List<Message> liste = new ArrayList<Message>();
        listeMessages.put(salon,liste);
    }
    
    /**
     * 
     * @return Le nombre de messages du salon en question
     */
    public int getNbMessages(String salon){
        if(listeMessages.containsKey(salon)){
            return listeMessages.get(salon).size();
        }
        else{
           return 0; 
        }
         
    }
    
    /**
     * 
     * @return La liste des messages du salon en question ou null
     */
    public List<Message> getMessages(String salon){
        return listeMessages.get(salon);
    }
    
    /**
     * Ajout du message en fin de liste du salon concerné et création du salon si besoin
     * @param mes messageà ajouter au salon
     */
    public void setNewMessage(String salon, Message mes){
        if(!listeMessages.containsKey(salon)){
            listeMessages.put(salon, new ArrayList<Message>());
        }          
        listeMessages.get(salon).add(mes);
    }
    /**
     * @return contenu du message en fonction de l'id
     */
    public String getMessage(String salon,int id){
    	return listeMessages.get("salon").get(id).getMessage();
    }
    
    /**
     * @return liste des salon
     */
    public ArrayList<String> getAllSalon(){
    	ArrayList<String> listeSalon = new ArrayList<String>();
    	for (String salon : listeMessages.keySet()){
    		listeSalon.add(salon);
    		
    	}
    	return listeSalon;
    	
    }
    
}
