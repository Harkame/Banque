package fr.harkame.banque.exception;

public class PersonnelNonAutoriseException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *             Message d'erreure a afficher
	 */
	public PersonnelNonAutoriseException(String message)
	{
		System.err.println(message);
	}
}
