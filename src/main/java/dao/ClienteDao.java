package dao;

import entidades.Cliente;
import java.util.List;

public interface ClienteDao {

    public boolean alta(Cliente cliente); // Crear nuevo cliente

    public boolean baja(int id); // Baja lógica del cliente

    public boolean modificar(Cliente cliente); // Modificar datos del cliente

    public List<Cliente> obtenerTodos(); // Listar todos los clientes activos

    public Cliente obtenerPorId(int id); // Obtener cliente por ID

}
