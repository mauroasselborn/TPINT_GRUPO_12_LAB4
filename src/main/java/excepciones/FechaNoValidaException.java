package excepciones;

public class FechaNoValidaException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FechaNoValidaException() {
        super("La fecha ingresada no es válida ya que es superior a la fecha actual");
    }

}

