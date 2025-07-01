package entidades;

import java.util.Date;

public class Cuota {

	private int id;
	private int numeroCuota;
	private float monto;
	private Date fechaPago;

	public Cuota() {

	}

	public Cuota(int id, int NumeroCuota, float Monto, Date FechaPago) {
		this.id = id;
		this.numeroCuota = NumeroCuota;
		this.monto = Monto;
		this.fechaPago = FechaPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

}
