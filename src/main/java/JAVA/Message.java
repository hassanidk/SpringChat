/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAVA;

/**
 *
 * @author thibom
 */
public class Message{
    private String pseudo;
    private String message;
    
    public Message(String pseudo, String message){
        this.pseudo = pseudo;
        this.message = message;
    } 
    
    public String getMessage(){
        return message;
    }
    
    public String getPseudo(){
        return pseudo;
    }
}
