package negocioImpl;

import negocio.ClienteNegocio;
import entidades.Cliente;
import java.util.List;
import java.util.ArrayList;

public class ClienteNegocioImpl implements ClienteNegocio {

    @Override
    public List<Cliente> listarClientes() {

        return new ArrayList<>(); 
    }
}