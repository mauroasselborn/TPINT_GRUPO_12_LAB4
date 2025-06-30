package entidades;

import java.util.Date;

public class Cuota {

	private int id;
	private int NumeroCuota;
	private float Monto;
	private Date FechaPago;
	
	
	public Cuota() {
		
	}
	
	public Cuota(int id, int NumeroCuota,float Monto, Date FechaPago) {
		this.id = id;
		this.NumeroCuota = NumeroCuota;
		this.Monto = Monto;
		this.FechaPago = FechaPago;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroCuota() {
		return NumeroCuota;
	}

	public void setNumeroCuota(int numeroCuota) {
		NumeroCuota = numeroCuota;
	}

	public float getMonto() {
		return Monto;
	}

	public void setMonto(float monto) {
		Monto = monto;
	}

	public Date getFechaPago() {
		return FechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}
	
	
}
