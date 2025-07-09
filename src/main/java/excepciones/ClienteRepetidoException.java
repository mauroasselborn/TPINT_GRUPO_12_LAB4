package excepciones;


public class ClienteRepetidoException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClienteRepetidoException(String mensaje) {
        super(mensaje);
    }

}

