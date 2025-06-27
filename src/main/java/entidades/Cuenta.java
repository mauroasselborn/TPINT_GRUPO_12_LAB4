package entidades;

public class Cuenta {
	private int id;
	private String numeroCuenta;
	private String cbu;
	private TipoCuenta tipoCuenta;
	private String fechaCreacion;
	private double saldo;
	private boolean activo;
	private Cliente cliente;

	public Cuenta() {
	}

	public Cuenta(int id, String numeroCuenta, String cbu, TipoCuenta tipoCuenta, String fechaCreacion, double saldo, boolean activo, Cliente cliente) {
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

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipocuenta) {
		this.tipoCuenta = tipocuenta;
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
