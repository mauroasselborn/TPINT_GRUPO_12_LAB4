package entidades;

import java.util.Date;

public class Prestamo {
	
	private int id;
	private Cuenta cuenta;
	private Date fecha;
	private float importePedido;
	private int cantidadCuotas;
	private float importeCuota;
	private int cuotaPendientes;
	private EstadoPrestamo estadoPrestamo;
	
	public Prestamo() {
	}
	
	public Prestamo(int id,Cuenta cuenta,Date fecha,float importePedido,int cantidadCuotas,float importeCuota,int cuotaPendientes, EstadoPrestamo estadoPrestamo) {
		this.id = id;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.importePedido = importePedido;
		this.cantidadCuotas = cantidadCuotas;
		this.importeCuota = importeCuota;
		this.cuotaPendientes = cuotaPendientes;
		this.estadoPrestamo = estadoPrestamo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImportePedido() {
		return importePedido;
	}

	public void setImportePedido(float importePedido) {
		this.importePedido = importePedido;
	}

	public int getCantidadCuotas() {
		return cantidadCuotas;
	}

	public void setCantidadCuotas(int cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	public float getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(float importeCuota) {
		this.importeCuota = importeCuota;
	}

	public int getCuotaPendientes() {
		return cuotaPendientes;
	}

	public void setCuotaPendientes(int cuotaPendientes) {
		this.cuotaPendientes = cuotaPendientes;
	}

	public EstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}

	public void setEstadoPrestamo(EstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}
	
	
}
