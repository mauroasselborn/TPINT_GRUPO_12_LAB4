package excepciones;

public class FechaNoValidaException extends Exception {

    public FechaNoValidaException() {
        super("La fecha ingresada no es válida ya que es superior a la fecha actual");
    }

}

