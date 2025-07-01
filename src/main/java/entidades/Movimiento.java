package entidades;

public class Movimiento {
	private int id;
    private String fecha;
    private String detalle;
    private TipoMovimiento tipoMovimiento;
    private double importe;
    private String tipo;
    private Cuenta cuenta;

    public Movimiento() {
    }

    public Movimiento(int id, String fecha, String detalle, TipoMovimiento tipoMovimiento, double importe, String tipo, Cuenta cuenta) {
        this.id = id;
        this.fecha = fecha;
        this.detalle = detalle;
        this.tipoMovimiento = tipoMovimiento;
        this.importe = importe;
        this.tipo = tipo;
        this.cuenta = cuenta;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

}
