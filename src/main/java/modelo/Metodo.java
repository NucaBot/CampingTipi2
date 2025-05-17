package modelo;

import java.sql.SQLException;

public class Metodo {

	// Atributos de la clase Método.

	private int idMetodo;
	private String metodo;

	// Constructor sin parámetros.

	public Metodo() {

	}

	// Constructor con parámetros.

	public Metodo(int idMetodo, String metodo) {

		this.idMetodo = idMetodo;
		this.metodo = metodo;

	}

	public Metodo(String metodo) {

		this.idMetodo = idMetodo;
		this.metodo = metodo;

	}
	// Aplicamos los getter and setter para cada atributo.

	public int getIdMetodo() {
		return idMetodo;
	}

	public void setIdMetodo(int idMetodo) {
		this.idMetodo = idMetodo;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public void obtenerDatos(int id) throws SQLException {
		
		Metodo aux = DaoMetodos.getInstance().obtenerDatos(id);
		
		this.idMetodo = aux.getIdMetodo();
		this.metodo = aux.getMetodo();
		
		
	}
	
	@Override
	public String toString() {
		return "Metodo [idMetodo=" + idMetodo + ", metodo=" + metodo + "]";
	}

}
