package modelo;

// Importación necesaria para trabajar con la BD.

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

/* Definición de la clase DaoReserva:
 *Es el DAO (Data Access Object) que maneja las operaciones de BD para las reservas.
 */

public class DaoReserva {
	
	

	private Connection con = null; // Conexión a la BD.
	public static DaoReserva instance = null; // Instancia única (patrón Singleton).
	
	public DaoReserva() throws SQLException {
		con = DBconexion.getConection(); // // Obtiene la conexión a la BD.
	}
	
	
	//Singleton: garantiza que haya una sola instancia de DaoReserva en toda la aplicación.


	
	public static DaoReserva getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoReserva();	
		}
		return instance;
		
	}
	
	
	public void insertar(Reserva r) throws SQLException {
		
		// Consulta SQL para insertar todos los datos de una reserva.
		
		String sql = "INSERT INTO reservas (idCliente,idParcela,adultos,menores,vehiculos,tomaLuz,tomaAgua,fechaEntrada,fechaSalida,metodoPago,estadoReserva) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, r.getCliente().getIdCliente());
		ps.setInt(2, r.getParcela().getIdParcela());
		ps.setInt(3, r.getAdulto() );
		ps.setInt(4, r.getMenor());
		ps.setInt(5, r.getVehiculo());
		ps.setInt(6, r.getTomaLuz());
		ps.setInt(7, r.getTomaAgua());
		
		Date fechaEntrada = java.sql.Date.valueOf(r.getFechaEntrada());
		ps.setDate(8, fechaEntrada);
		
		Date fechaSalida = java.sql.Date.valueOf(r.getFechaSalida());
		ps.setDate(9,fechaSalida);
		ps.setInt(10, r.getEstado().getIdEstado());		
		ps.setInt(11, r.getMetodo().getIdMetodo());
		
		
		ps.executeUpdate(); // Ejecuta la inserción.
		
		ps.close(); // Cierra el PreparedStatement.
		
	}
	
	
	// Devuelve todas las reservas de la base de datos como una lista.
	
	public ArrayList<Reserva> listar() throws SQLException{
		
		
		String sql;
		PreparedStatement ps;
	
			
			sql = "SELECT * from reservas"; // Consulta todas las reservas.
			ps = con.prepareStatement(sql);
		
		
			ResultSet rs = ps.executeQuery(); // Ejecuta la consulta.
		
		// Inicializa la lista de resultados.
			
		ArrayList<Reserva> result = null;
		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
			
			Cliente c = new Cliente();
			c.obtenerDatos(rs.getInt(2)); // Obtiene los datos del cliente relacionado.
			
			
			Parcela p = new Parcela();
			p.obtenerDatos(rs.getInt(3)); // Obtiene los datos de la parcela relacionada.
			
			Estado e = new Estado();
			e.obtenerDatos(rs.getInt(11)); // Obtiene el estado de la reserva.
			
			Metodo m = new Metodo();
			m.obtenerDatos(rs.getInt(12)); // Obtiene el método de pago.
			
		result.add(new Reserva(rs.getInt(1),c, p, rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(5), rs.getInt(8), rs.getDate(9).toString(), rs.getDate(10).toString(), m, e));
		}
		rs.close(); // Cierra el ResultSet.
		ps.close(); // Cierra el PreparedStatement.
		return result; // Devuelve la lista de reservas.
		
		
	}
	
	
	public String listarEnJson() throws SQLException {
		
		Gson gson = new Gson();
		return gson.toJson(this.listar());
		
	}
	
	
	
	
	
	
	

}
