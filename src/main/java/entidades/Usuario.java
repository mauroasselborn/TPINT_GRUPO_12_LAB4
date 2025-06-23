package entidades;

public class Usuario {
	private int id;
	private String nombreUsuario;
	private String contrasena;
	private TipoUsuario tipoUsuario;
	private boolean activo;

	public Usuario() {}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNombreUsuario() { return nombreUsuario; }
	public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

	public String getContrasena() { return contrasena; }
	public void setContrasena(String contrasena) { this.contrasena = contrasena; }

	public TipoUsuario getTipoUsuario() { return tipoUsuario; }
	public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

	public boolean isActivo() { return activo; }
	public void setActivo(boolean activo) { this.activo = activo; }
}
