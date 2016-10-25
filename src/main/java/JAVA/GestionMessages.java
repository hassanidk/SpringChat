/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAVA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thibom
 */
public class GestionMessages {
    
    static Map<String,List<Message>> listeMessages;
    private String salon;
    
    /**
     * Constructeur par défaut
     */
    public GestionMessages(){
        
    }
    
    /**
     * Defini le salon courant pour l'instance
     * @param salon le nom du salon
     */
    public void setSalon(String salon){
        this.salon = salon;
    }
    
    /**
     * 
     * @return Le nombre de messages du salon en question
     */
    public int getNbMessages(){
        if(GestionMessages.listeMessages.containsKey(this.salon)){
            return GestionMessages.listeMessages.get(this.salon).size();
        }
        else{
           return 0; 
        }
         
    }
    
    /**
     * 
     * @return La liste des messages du salon en question ou null
     */
    public List<Message> getMessages(){
        return GestionMessages.listeMessages.get(salon);
    }
    
    /**
     * Ajout du message en fin de liste du salon concerné et création du salon si besoin
     * @param mes messageà ajouter au salon
     */
    public void setNewMessage(Message mes){
        if(!GestionMessages.listeMessages.containsKey(this.salon)){
            GestionMessages.listeMessages.put(this.salon, new ArrayList<Message>());
        }          
        GestionMessages.listeMessages.get(this.salon).add(mes);
    }
    
    
}
