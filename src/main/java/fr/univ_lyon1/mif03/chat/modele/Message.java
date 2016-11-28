/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univ_lyon1.mif03.chat.modele;

/**
 *
 * @author thibom
 */
public class Message{
    private String pseudo;
    private String message;
    public Message(){}
    /**
     * Constructeur par défaut
     * @param pseudo le pseudo de l'utilisateur entrant le message
     * @param message le contenu textuel du message
     */
    public Message(String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    } 
    
    /**
     * 
     * @return le contenu textuel du message
     */
    public String getMessage(){
        return message;
    }
    
    /**
     * 
     * @return Le pseudo de l'auteur du message
     */
    public String getPseudo(){
        return pseudo;
    }
}
