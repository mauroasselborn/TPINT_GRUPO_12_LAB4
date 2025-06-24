package entidades;

public class Cuenta {
	private int id;
	private String numeroCuenta;
	private String cbu;
	private int tipoCuenta;
	private String fechaCreacion;
	private double saldo;
	private boolean activo;
	private Cliente cliente;

	public Cuenta() {
	}

	public Cuenta(int id, String numeroCuenta, String cbu, int tipoCuenta, String fechaCreacion, double saldo, boolean activo, Cliente cliente) {
		this.id = id;
		this.numeroCuenta = numeroCuenta;
		this.cbu = cbu;
		this.tipoCuenta = tipoCuenta;
		this.fechaCreacion = fechaCreacion;
		this.saldo = saldo;
		this.activo = activo;
		this.cliente = cliente;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public int getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
