package modelo;

import java.sql.SQLException;

public class Estado {

	// Atributos de la clase Estado.

	private int idEstado;
	private String estado;

	// Constructor sin parámetros.

	public Estado() {

	}
	// Constructor con parámetros.

	public Estado(int idEstado, String estado) {
		this.idEstado = idEstado;
		this.estado = estado;
	}

	public Estado(String estado) {
		this.estado = estado;
	}

	// Aplicamos los getter and setter para cada atributo.

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void obtenerDatos(int id) throws SQLException {
		
		Estado aux = DaoEstados.getInstance().obtenerDatos(id);
		
		this.idEstado = aux.getIdEstado();
		this.estado = aux.getEstado();
		
		
	}

	@Override
	public String toString() {
		return "Estado [idEstado=" + idEstado + ", estado=" + estado + "]";
	}

	
	
	
}