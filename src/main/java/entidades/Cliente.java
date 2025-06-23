package entidades;

public class Cliente {
	private int id;
	private String dni;
	private String cuil;
	private String nombre;
	private String apellido;
	private String sexo;
	private String nacionalidad;
	private String fechaNacimiento;
	private String direccion;
	private String correoElectronico;
	private String telefono;
	private Localidad localidad;
	private Provincia provincia;
	private Usuario usuario;
	private boolean activo;

	public Cliente() {}

	// Getters y setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getDni() { return dni; }
	public void setDni(String dni) { this.dni = dni; }

	public String getCuil() { return cuil; }
	public void setCuil(String cuil) { this.cuil = cuil; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellido() { return apellido; }
	public void setApellido(String apellido) { this.apellido = apellido; }

	public String getSexo() { return sexo; }
	public void setSexo(String sexo) { this.sexo = sexo; }

	public String getNacionalidad() { return nacionalidad; }
	public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

	public String getFechaNacimiento() { return fechaNacimiento; }
	public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }

	public String getCorreoElectronico() { return correoElectronico; }
	public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }

	public Localidad getLocalidad() { return localidad; }
	public void setLocalidad(Localidad localidad) { this.localidad = localidad; }

	public Provincia getProvincia() { return provincia; }
	public void setProvincia(Provincia provincia) { this.provincia = provincia; }

	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }

	public boolean isActivo() { return activo; }
	public void setActivo(boolean activo) { this.activo = activo; }
}
