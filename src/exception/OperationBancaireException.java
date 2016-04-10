package banque_executable.exception;
/**
 * 
 * @author Louis Daviaud
 *
 */
public class OperationBancaireException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 *            Message d'erreure a afficher
	 */
	public OperationBancaireException(String message) {
		System.err.println(message);
	}
}
