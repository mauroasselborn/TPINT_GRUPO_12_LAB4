package entidades;

import java.util.Date;

public class Prestamo {
	private int id;
	private Cliente cliente;
	private Cuenta cuenta;
	private Date fechaAlta;
	private double importePedido;
	private int cantidadCuotas;
	private double importePorCuota;
	private String estado;
	private boolean activo;

	public Prestamo() {

	}

	public Prestamo(int id, Cliente cliente, Cuenta cuenta, Date fechaAlta, double importePedido, int cantidadCuotas, double importePorCuota, String estado, boolean activo) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.fechaAlta = fechaAlta;
		this.importePedido = importePedido;
		this.cantidadCuotas = cantidadCuotas;
		this.importePorCuota = importePorCuota;
		this.estado = estado;
		this.activo = activo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(double importePedido) {
		this.importePedido = importePedido;
	}

	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public double getImportePorCuota() {
		return importePorCuota;
	}

	public void setImportePorCuota(double importePorCuota) {
		this.importePorCuota = importePorCuota;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
