package modelo; // Define el paquete donde está la clase.

// Importación necesaria para conectar a la BD.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoCliente {

	private Connection con = null; // Conexión a la BD.
	public static DaoCliente instance = null; // Instancia única (patrón Singleton).

	// Constructor privado (solo se puede crear dentro de la clase) que obtiene la conexión.
	

	public DaoCliente() throws SQLException {
		con = DBconexion.getConection(); // Se conecta a la BD usando una clase DBconexion.
	}

	// Método para obtener la única instancia de DaoCliente (Singleton).

	public static DaoCliente getInstance() throws SQLException {
		if (instance == null) { // Si no existe aún, la crea.
			instance = new DaoCliente();
		}
		return instance;

	}

	// Método para insertar un nuevo cliente en la BD.

	public void insertar(Cliente c) throws SQLException {

		PreparedStatement ps = con.prepareStatement(
				"INSERT INTO clientes (nombre,apellidos,password,dni, direccion,telefono, fechaNacimiento,correoElectronico,preferenciasCliente) VALUES (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getApellidos());
		ps.setString(3, c.getPassword());
		ps.setString(4, c.getDni());
		ps.setString(5, c.getDireccion());
		ps.setString(6, c.getTelefono());
		ps.setString(7, c.getFechaNacimiento());
		ps.setString(8, c.getCorreoElectronico());
		ps.setString(9, c.getPreferenciasCliente());

		int filas = ps.executeUpdate(); // Ejecuta la inserción.

		ps.close(); // Cierra el statement.

	}

	// Método para actualizar un cliente existente en la BD.

	public int actualizar(Cliente c) throws SQLException {

		int filas = 0;

		PreparedStatement ps = con.prepareStatement(
				"UPDATE clientes SET nombre=?,apellidos=?,password=?,dni=?, direccion=?,telefono=?, fechaNacimiento=?,correoElectronico=?,preferenciasCliente=? WHERE idcliente=?");
		ps.setString(1, c.getNombre());
		ps.setString(2, c.getApellidos());
		ps.setString(3, c.getPassword());
		ps.setString(4, c.getDni());
		ps.setString(5, c.getDireccion());
		ps.setString(6, c.getTelefono());
		ps.setString(7, c.getFechaNacimiento());
		ps.setString(8, c.getCorreoElectronico());
		ps.setString(9, c.getPreferenciasCliente());
		ps.setInt(10, c.getIdCliente());

		filas = ps.executeUpdate(); // Ejecuta el Update.

		ps.close(); // Cierra el statement.

		return filas; // Devuelve cuántas filas fueron actualizadas.
	}

	// Método para borrar un cliente de la BD por su id.

	public void borrar(int id) throws SQLException {

		PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE idCliente=?");
		ps.setInt(1, id);

		ps.executeUpdate();

		ps.close();

	}
	// Método para listar clientes, con opción de aplicar un filtro.

	public ArrayList<Cliente> listar(String filtro) throws SQLException {

		String sql;
		PreparedStatement ps;
		if (filtro != null) {

			sql = "SELECT * from clientes WHERE %?% LIKE";
			ps = con.prepareStatement(sql);
			ps.setString(0, filtro);

		} else {
			sql = "SELECT * from clientes;";
			ps = con.prepareStatement(sql);

		}

		ResultSet rs = ps.executeQuery(); // Ejecuta la consulta.

		ArrayList<Cliente> result = null;

		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();

			// Crea un objeto Cliente por cada fila del resultado y lo agrega a la lista.

			result.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
		}
		rs.close();
		ps.close();
		return result;

	}

	// Método que devuelve los clientes en formato JSON usando Gson.

	public String listarEnJson(String filtro) throws SQLException {

		Gson gson = new Gson();
		String JSON;

		JSON = gson.toJson(this.listar(filtro));

		return JSON;
	}

	// Método para convertir un cliente en JSON a partir de su id.

	public String toJson(int id) throws SQLException {

		String sql = "SELECT * from clientes WHERE idCliente=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next(); // Mover al primer (y único) resultado.

		Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

		Gson gson = new Gson();

		return gson.toJson(c);

	}

// Método para obtener el objeto Cliente a partir de su id.

	public Cliente obtenerDatos(int id) throws SQLException {

		String sql = "SELECT * from clientes WHERE idCliente=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		rs.next(); // Mover al primer (y único) resultado.

		Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
				rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));

		return c; // Devuelve el cliente encontrado.
	}

}
