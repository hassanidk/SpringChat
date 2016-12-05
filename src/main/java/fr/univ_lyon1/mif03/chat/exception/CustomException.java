package fr.univ_lyon1.mif03.chat.exception;

public class CustomException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private String errMessage;
	/**
	 * Classe qui permet de "catch" les exception
	 * @param errMessage, récupere le message d'erreur
	 */
	public CustomException(String errMessage){
		this.errMessage = errMessage;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
}
