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
	private int cuotasPendientes;
	private int idEstado;

	public Prestamo() {
	}

	public Prestamo(int id, Cliente cliente, Cuenta cuenta, Date fechaAlta, double importePedido, int cantidadCuotas, double importePorCuota, int cuotasPendientes, int idEstado) {
		this.id = id;
		this.cliente = cliente;
		this.cuenta = cuenta;
		this.fechaAlta = fechaAlta;
		this.importePedido = importePedido;
		this.cantidadCuotas = cantidadCuotas;
		this.importePorCuota = importePorCuota;
		this.cuotasPendientes = cuotasPendientes;
		this.idEstado = idEstado;
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

	public int getCuotasPendientes() {
		return cuotasPendientes;
	}

	public void setCuotasPendientes(int cuotasPendientes) {
		this.cuotasPendientes = cuotasPendientes;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
}
