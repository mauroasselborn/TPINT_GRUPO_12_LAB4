package excepciones;

import entidades.Cliente;

public class ClienteRepetidoException extends Exception {

    public ClienteRepetidoException(String mensaje) {
        super(mensaje);
    }

}

