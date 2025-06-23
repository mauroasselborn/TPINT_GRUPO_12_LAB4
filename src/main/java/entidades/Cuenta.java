package entidades;

public class Cuenta {
	private int id;
	private String numeroCuenta;
	private String cbu;
	private double saldo;
	private String fechaCreacion;
	private TipoCuenta tipoCuenta;
	private Cliente cliente;
	private boolean activo;

	public Cuenta() {}

	// getters y setters
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNumeroCuenta() { return numeroCuenta; }
	public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

	public String getCbu() { return cbu; }
	public void setCbu(String cbu) { this.cbu = cbu; }

	public TipoCuenta getTipoCuenta() { return tipoCuenta; }
	public void setTipoCuenta(TipoCuenta tipoCuenta) { this.tipoCuenta = tipoCuenta; }

	public double getSaldo() { return saldo; }
	public void setSaldo(double saldo) { this.saldo = saldo; }

	public String getFechaCreacion() { return fechaCreacion; }
	public void setFechaCreacion(String fechaCreacion) { this.fechaCreacion = fechaCreacion; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public boolean isActivo() { return activo; }
	public void setActivo(boolean activo) { this.activo = activo; }
}
