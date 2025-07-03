package entidades;

import java.util.Date;

public class Cuota {

	private int id;
	private int numeroCuota;
	private double monto;
	private Date fechaPago; // puede ser null si no está pagada
	private Prestamo prestamo;
	private boolean activo;

	public Cuota() {

	}

	public Cuota(int id, int numeroCuota, float Monto, Date FechaPago) {
		this.id = id;
		this.numeroCuota = numeroCuota;
		this.monto = Monto;
		this.fechaPago = FechaPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNroCuota() {
		return numeroCuota;
	}

	public void setNroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

}
