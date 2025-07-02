package entidades;

public class EstadoPrestamo {

	private int id;
	private String descripcion;
	
	public EstadoPrestamo() {
	}
	
	public EstadoPrestamo(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	//getters y Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
