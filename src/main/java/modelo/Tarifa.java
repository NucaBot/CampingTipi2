package modelo;

public class Tarifa {

	// Atributos de la clase Tarifa.

	private int idTarifa;
	private double vehiculo;
	private double adulto;
	private double menor;
	private double tomaLuz;
	private double tomaAgua;

	// Constructor sin parámetros, permite crear una tarifa sin inicializar sus
	// valores.

	public Tarifa() {

	}

	// Constructor para una nueva tarifa sin idTarifa (id se genera en la BD).

	public Tarifa(int idTarifa, double vehiculo, double adulto, double menor, double tomaLuz, double tomaAgua) {
		this.idTarifa = idTarifa;
		this.vehiculo = vehiculo;
		this.adulto = adulto;
		this.menor = menor;
		this.tomaLuz = tomaLuz;
		this.tomaAgua = tomaAgua;

	}

	public Tarifa(double vehiculo, double adulto, double menor, double tomaLuz, double tomaAgua) {
		this.vehiculo = vehiculo;
		this.adulto = adulto;
		this.menor = menor;
		this.tomaLuz = tomaLuz;
		this.tomaAgua = tomaAgua;

	}

	// Aplicamos los getter and setter para cada atributo.

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	public double getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(double vehiculo) {
		this.vehiculo = vehiculo;
	}

	public double getAdulto() {
		return adulto;
	}

	public void setAdulto(double adulto) {
		this.adulto = adulto;
	}

	public double getMenor() {
		return menor;
	}

	public void setMenor(double menor) {
		this.menor = menor;
	}

	public double getTomaLuz() {
		return tomaLuz;
	}

	public void setTomaLuz(double tomaLuz) {
		this.tomaLuz = tomaLuz;
	}

	public double getTomaAgua() {
		return tomaAgua;
	}

	public void setTomaAgua(double tomaAgua) {
		this.tomaAgua = tomaAgua;
	}

	@Override
	public String toString() {
		return "Tarifa [idTarifa=" + idTarifa + ", vehiculo=" + vehiculo + ", adulto=" + adulto + ", menor=" + menor
				+ ", tomaLuz=" + tomaLuz + ", tomaAgua=" + tomaAgua + "]";
	}

}
