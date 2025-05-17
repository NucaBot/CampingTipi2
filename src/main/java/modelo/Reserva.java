package modelo;

//Importa SQLException para posibles errores en operaciones de la BD.

import java.sql.SQLException;

public class Reserva {

	// Atributos de la clase Reserva.

	private int idReserva;
	private Cliente cliente;
	private Parcela parcela;
	private int adulto;
	private int menor;
	private int vehiculo;
	private int tomaLuz;
	private int tomaAgua;
	private String fechaEntrada;
	private String fechaSalida;
	private Metodo metodo;
	private Estado estado;

	// Constructor sin parámetros. Permite crear una reserva sin inicializar valores
	// aún.

	public Reserva() {

	}

	// Constructor con parámetros.

	public Reserva(int idReserva, Cliente cliente, Parcela parcela, int adulto, int menor, int vehiculo, int tomaLuz,
			int tomaAgua, String fechaEntrada, String fechaSalida, Metodo metodo, Estado estado) {
		this.idReserva = idReserva;
		this.cliente = cliente;
		this.parcela = parcela;
		this.adulto = adulto;
		this.menor = menor;
		this.vehiculo = vehiculo;
		this.tomaLuz = tomaLuz;
		this.tomaAgua = tomaAgua;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.metodo = metodo;
		this.estado = estado;

	}

	// Constructor para nuevas reservas (sin idReserva aún), antes de insertar en la
	// BD.

	public Reserva(Cliente cliente, Parcela parcela, int adulto, int menor, int vehiculo, int tomaLuz, int tomaAgua,
			String fechaEntrada, String fechaSalida, Metodo metodo, Estado estado) {

		this.cliente = cliente;
		this.parcela = parcela;
		this.adulto = adulto;
		this.menor = menor;
		this.vehiculo = vehiculo;
		this.tomaLuz = tomaLuz;
		this.tomaAgua = tomaAgua;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.metodo = metodo;
		this.estado = estado;

	}

	// Aplicamos los getter and setter para cada atributo.

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Parcela getParcela() {
		return parcela;
	}

	public void setParcela(Parcela parcela) {
		this.parcela = parcela;
	}

	public int getAdulto() {
		return adulto;
	}

	public void setAdulto(int adulto) {
		this.adulto = adulto;
	}

	public int getMenor() {
		return menor;
	}

	public void setMenor(int menor) {
		this.menor = menor;
	}

	public int getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(int vehiculo) {
		this.vehiculo = vehiculo;
	}

	public int getTomaLuz() {
		return tomaLuz;
	}

	public void setTomaLuz(int tomaLuz) {
		this.tomaLuz = tomaLuz;
	}

	public int getTomaAgua() {
		return tomaAgua;
	}

	public void setTomaAgua(int tomaAgua) {
		this.tomaAgua = tomaAgua;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Metodo getMetodo() {
		return metodo;
	}

	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	// Método para insertar en la BD.

	public void insertar() throws SQLException {

		DaoReserva.getInstance().insertar(this);

	}

	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", cliente=" + cliente + ", parcela=" + parcela + ", adulto="
				+ adulto + ", menor=" + menor + ", vehiculo=" + vehiculo + ", tomaLuz=" + tomaLuz + ", tomaAgua="
				+ tomaAgua + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", metodo=" + metodo
				+ ", estado=" + estado + "]";
	}

}