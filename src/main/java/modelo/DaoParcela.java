package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DaoParcela {

	

	
	private Connection con = null;
	public static DaoParcela instance = null;
	
	public DaoParcela() throws SQLException {
		con = DBconexion.getConection();
	}
	
	
	//Singleton
	public static DaoParcela getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoParcela();	
		}
		return instance;
		
	}
	
	
	public  void insertar(Parcela p) throws SQLException {
		
					
		PreparedStatement ps =  con.prepareStatement("INSERT INTO parcelas (tipoParcela,descripcion,foto,precioNoche) VALUES (?,?,?,?)");	
	
		ps.setString(1, p.getTipoParcela());
		ps.setString(2, p.getDescripcion());
		ps.setString(3, p.getFoto());
		ps.setDouble(4, p.getPrecioNoche());
		
		
		
		int filas = ps.executeUpdate();
		
		ps.close();
		
	}
	
	
	public int actualizar(Parcela p) throws SQLException {
		
		int filas = 0;
		/*
		PreparedStatement ps =  con.prepareStatement("UPDATE parcelas SET nombre=?,apellidos=?,password=?,dni=?, direccion=?,telefono=?, fechaNacimiento=?,correoElectronico=?,preferenciasCliente=? WHERE idcliente=?");	
		ps.setString(1,p.getNombre());
		ps.setString(2,p.getApellidos() );
		ps.setString(3,c.getPassword());
		ps.setString(4,c.getDni());
		ps.setString(5,c.getDireccion());
		ps.setString(6,c.getTelefono());
		ps.setString(7,c.getFechaNacimiento());
		ps.setString(8,c.getCorreoElectronico());
		ps.setString(9,c.getPreferenciasCliente());
		ps.setInt(10,c.getIdCliente());
		
	
		filas = ps.executeUpdate();
		
		ps.close();
		*/
	return filas;
	}
	
	
	public void borrar(int id) throws SQLException {
		
		PreparedStatement ps =  con.prepareStatement("DELETE FROM clientes WHERE idCliente=?");
		ps.setInt(1, id);
		
		ps.executeUpdate();
		
		ps.close();

	}
	
	
public ArrayList<Parcela> listar(String filtro) throws SQLException {
		
		String sql;
		PreparedStatement ps;
		if(filtro != null) {
			
			sql = "SELECT * from parcelas WHERE %?% LIKE";
			ps = con.prepareStatement(sql);
			ps.setString(0, filtro);

		}else {
			sql = "SELECT * from parcelas;";
			ps = con.prepareStatement(sql);
		
			
		}
	
		
		ResultSet rs = ps.executeQuery();
		
		
		ArrayList<Parcela> result = null;
		
		while (rs.next()) {
			if (result == null)
				result = new ArrayList<>();
		result.add(new Parcela(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getDouble(5)));
		}
		rs.close();
		ps.close();
		return result;
		
	}


public String listarEnJson(String filtro) throws SQLException {
	
	Gson gson = new Gson();
	String JSON;
	
	JSON = gson.toJson(this.listar(filtro));
		
	return JSON; 			
}


public String listarSelect() throws SQLException {
	
	String sql;
	PreparedStatement ps;
	sql = "SELECT * from parcelas;";
	ps = con.prepareStatement(sql);

	
	ArrayList<Parcela> result = null;
	ResultSet rs = ps.executeQuery();

	while (rs.next()) {
		if (result == null)
			result = new ArrayList<>();
	result.add(new Parcela(rs.getInt(1),rs.getString(2)));
	}
	rs.close();
	ps.close();

	Gson gson = new Gson();
	return gson.toJson(result);

}


public String toJson(int id) throws SQLException {
	
	String sql = "SELECT * from parcelas WHERE idParcela=?";
	
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, id);
	
	ResultSet rs = ps.executeQuery();
	
	rs.next();
	
	Parcela p = new Parcela(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getDouble(5));
	
	Gson gson = new Gson();
	ps.close();
	
	return gson.toJson(p);
	
}


public Parcela obtenerDatos(int id) throws SQLException {
	
String sql = "SELECT * from parcelas WHERE idParcela=?";
	
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setInt(1, id);
	
	ResultSet rs = ps.executeQuery();
	
	rs.next();
	
	Parcela p = new Parcela(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getDouble(5));
	
	return p;

}

	
}
